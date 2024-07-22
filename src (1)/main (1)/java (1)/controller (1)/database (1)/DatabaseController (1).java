package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.PasswordEncryptionWithAES;
import model.ProductModel;
import model.UserModel;
import model.UserProfile;
import model.orderModel;
import util.StringUtils;

public class DatabaseController {

	public Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3306/timekeepers_db";
		String user = "root";
		String pass = "";
		return DriverManager.getConnection(url, user, pass);
	}
	
	// adding user into the system database
	public int addUser(UserModel userModel) {
		try (Connection con = getConnection()) {
			PreparedStatement usr = getConnection().prepareStatement(StringUtils.INSERT_USER);
			
			usr.setString(1, userModel.getFirstName());
			usr.setString(2, userModel.getLastName());
			usr.setString(3, userModel.getUsername());			
			usr.setDate(4, Date.valueOf(userModel.getDob()));
			usr.setString(5, userModel.getGender());
			usr.setString(6, userModel.getPhoneNumber());
			usr.setString(7, userModel.getEmail());
			usr.setString(8, userModel.getAddress());
			usr.setString(9, PasswordEncryptionWithAES.encrypt(userModel.getUsername(), userModel.getPassword()));
			usr.setString(10, userModel.getImageUrlFromPart());

			int result = usr.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
	}
	
	public boolean isExistingUser(String username) {
        try (Connection con = getConnection()) {
            // Check if username already exists
            PreparedStatement usernameCheckStmt = con.prepareStatement("SELECT * FROM user_info WHERE username = ?");
            usernameCheckStmt.setString(1, username);
            ResultSet usernameResult = usernameCheckStmt.executeQuery();
            if (usernameResult.next()) {
                return true;
            }

            // If none of the above conditions match, user does not exist
            return false;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return false; // Return false in case of any exception
        }
    }

	public boolean isExistingNumber(String phoneNumber) {
        try (Connection con = getConnection()) {
            // Check if phone number already exists
            PreparedStatement phoneNumberCheckStmt = con.prepareStatement("SELECT * FROM user_info WHERE phone_number = ?");
            phoneNumberCheckStmt.setString(1, phoneNumber);
            ResultSet phoneNumberResult = phoneNumberCheckStmt.executeQuery();
            if (phoneNumberResult.next()) {
                return true;
            }

            // If none of the above conditions match, user does not exist
            return false;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return false; // Return false in case of any exception
        }
    }
	
	public boolean isExistingEmail(String email) {
        try (Connection con = getConnection()) {
            // Check if email already exists
            PreparedStatement emailCheckStmt = con.prepareStatement("SELECT * FROM user_info WHERE email_address = ?");
            emailCheckStmt.setString(1, email);
            ResultSet emailResult = emailCheckStmt.executeQuery();
            if (emailResult.next()) {
                return true;
            }

            // If none of the above conditions match, user does not exist
            return false;
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return false; // Return false in case of any exception
        }
    }
		
	public int getUserLoginInfo(String username, String password) {
        try (Connection con = getConnection()) {
            PreparedStatement usr = con.prepareStatement(StringUtils.GET_LOGIN_USER_INFO);
            usr.setString(1, username);
            ResultSet result = usr.executeQuery();

            if (result.next()) {
                // Retrieve the encrypted password from the database
                String usernameDb = result.getString("username");
                String passwordDb = result.getString("password");
                // Decrypt the retrieved password
                String decryptedPassword = PasswordEncryptionWithAES.decrypt(passwordDb, username);
                
                // Check if decrypted password matches the provided password
                if (decryptedPassword != null && usernameDb.equals(username) && decryptedPassword.equals(password)) {
                    // User name and password match in the database
                    String role = result.getString("role");
                    return role.equals("customer") ? 1 : (role.equals("admin") ? 2 : 0);
                }
            }
            
            return 0; // User not found or incorrect password
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); // Log the exception for debugging
            return -1; // Error occurred
        }
    }
	
//	public int getStudentLoginInfo(LoginModel loginModel) {
//        // Try-catch block to handle potential SQL or ClassNotFound exceptions
//        try {
//            // Prepare a statement using the predefined query for login check
//            PreparedStatement st = getConnection().prepareStatement(StringUtils.QUERY_LOGIN_USER_CHECK);
//
//            // Set the username in the first parameter of the prepared statement
//            st.setString(1, loginModel.getUsername());
//
//            // Execute the query and store the result set
//            ResultSet result = st.executeQuery();
//
//            // Check if there's a record returned from the query
//            if (result.next()) {
//                // Get the username from the database
//                String userDb = result.getString(StringUtils.USER_NAME);
//
//                // Get the password from the database
//                String encryptedPwd = result.getString(StringUtils.PASSWORD);
//
//                String decryptedPwd = PasswordEncryptionWithAes.decrypt(encryptedPwd, userDb);
//                // Check if the username and password match the credentials from the database
//                if (userDb.equals(loginModel.getUsername()) && decryptedPwd.equals(loginModel.getPassword())) {
//                    // Login successful, return 1
//                    return 1;
//                } else {
//                    // Username or password mismatch, return 0
//                    return 0;
//                }
//            } else {
//                // Username not found in the database, return -1
//                return -1;
//            }
//
//            // Catch SQLException and ClassNotFoundException if they occur
//        } catch (SQLException | ClassNotFoundException ex) {
//            // Print the stack trace for debugging purposes
//            ex.printStackTrace();
//            // Return -2 to indicate an internal error
//            return -2;
//        }
//    }
	
	public ArrayList<UserModel> getAllUsersInfo(){
        try {
            PreparedStatement stmt = getConnection()
                    .prepareStatement(StringUtils.GET_ALL_USER_LIST);
            ResultSet result = stmt.executeQuery();

            ArrayList<UserModel> users = new ArrayList<UserModel>();

            while(result.next()) {
            	UserModel usr = new UserModel();
//            	usr.setUsername(result.getString("user_id"));
            	usr.setFirstName(result.getString("first_name"));
            	usr.setLastName(result.getString("last_name"));
            	usr.setUsername(result.getString("username"));
            	usr.setDob(result.getDate("dob").toLocalDate());
            	usr.setGender(result.getString("gender"));
            	usr.setAddress(result.getString("address"));
//            	usr.setImageUrlFromPart(result.getString("image"));
                users.add(usr);
            }
            return users;
        }catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    
	// adding products into the system database
    public int addProduct(ProductModel productModel) {
        try (Connection con = getConnection()) {
            PreparedStatement prod = getConnection().prepareStatement(StringUtils.ADD_PRODUCT);
            
            prod.setString(1, productModel.getProduct_id());
            prod.setString(2, productModel.getProduct_name());
            prod.setString(3, productModel.getUnit_price());          
            prod.setString(4, productModel.getProduct_quantity());
            prod.setString(5, productModel.getProduct_availability());
            prod.setString(6, productModel.getVendor_id());
            //added by bipul
            prod.setString(7, productModel.getImageUrlFromPart());

			int result = prod.executeUpdate();
			return result > 0 ? 1 : 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return -1;
		}
    }
    
    
	public ArrayList<ProductModel> getAllProductInfo(){
		try {
			PreparedStatement con = getConnection()
					.prepareStatement("SELECT * FROM product");
			ResultSet result = con.executeQuery(); //query trigger huncha 
			
			ArrayList<ProductModel> products = new ArrayList<ProductModel>();
			
			while(result.next()) {
				ProductModel prod = new ProductModel();
				prod.setProduct_id(result.getString("product_id"));
				prod.setProduct_name(result.getString("product_name"));
				prod.setUnit_price(result.getString("unit_price"));
				prod.setProduct_quantity(result.getString("product_quantity"));
				prod.setProduct_availability(result.getString("product_availability"));
				prod.setVendor_id(result.getString("vendor_id"));
				prod.setImageUrlFromPart(result.getString("image"));
				
				products.add(prod);
			}
			return products;
		}catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	//added by bipul
	public int deleteProductInfo(String product_id) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.QUERY_DELETE_PRODUCT);
			st.setString(1, product_id);
			return st.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	public int updateProductInfo(ProductModel product) {
		try (Connection con = getConnection()) {
			PreparedStatement st = con.prepareStatement(StringUtils.UPDATE_PRODUCT_INFO);
//			st.setString(1, product.getProduct_id());
			st.setString(1, product.getProduct_name());
			st.setString(2, product.getUnit_price());
			st.setString(3, product.getProduct_quantity());
			st.setString(4, product.getProduct_availability());
			st.setString(5, product.getVendor_id());
			st.setString(6, product.getProduct_id());
			
			System.out.println("getProduct_id= "+product.getProduct_id());
			
			
			int result = st.executeUpdate();
			
			System.out.println("Result= "+result);
			return result > 0 ? 1: 0;
		} catch (SQLException | ClassNotFoundException ex) {
			ex.printStackTrace(); // Log the exception for debugging
			return -1;
		}
	}
	
	public ProductModel getProductById(String productId) {
	    try {
	        PreparedStatement stmt = getConnection()
	                .prepareStatement("SELECT * FROM product WHERE product_id = ?");
	        stmt.setString(1, productId);
	        ResultSet result = stmt.executeQuery();

	        if (result.next()) {
	            ProductModel prod = new ProductModel();
	            prod.setProduct_name(result.getString("product_name"));
	            prod.setUnit_price(result.getString("unit_price"));
	            prod.setProduct_quantity(result.getString("product_quantity"));
	            prod.setProduct_availability(result.getString("product_availability"));
	            prod.setVendor_id(result.getString("vendor_id"));
	            prod.setProduct_id(result.getString("product_id"));
	            prod.setImageUrlFromPart(result.getString("image"));
	            return prod;
	        } else {
	            return null; // Product with given ID not found
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
	public ArrayList<orderModel> getAllOrderInfo(){
        try {
            PreparedStatement stmt = getConnection()
                    .prepareStatement(StringUtils.GET_ALL_ORDER_LIST);
            ResultSet result = stmt.executeQuery();

            ArrayList<orderModel> orders = new ArrayList<orderModel>();

            while(result.next()) {
            	orderModel odr = new orderModel();
//            	usr.setUsername(result.getString("user_id"));
            	odr.setOrderId(result.getString("order_id"));
            	odr.setOrderDate(result.getDate("order_date").toLocalDate());
            	odr.setOrderTotal(result.getInt("order_total"));
            	odr.setInvoiceId(result.getString("invoice_id"));
            	odr.setUserId(result.getString("user_id"));
                orders.add(odr);
            }
            return orders;
        }catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
	
	public List<ProductModel> searchProductByName(String product_name) {
		List<ProductModel> productList = new ArrayList<ProductModel>();
	    try (Connection connection = getConnection();
	         PreparedStatement stmt = connection.prepareStatement("SELECT * FROM product WHERE product_name LIKE ?")) {
	        stmt.setString(1, "%" + product_name + "%");
	        ResultSet result = stmt.executeQuery();

	        while (result.next()) {
	            ProductModel prod = new ProductModel();
	            prod.setProduct_name(result.getString("product_name"));
	            prod.setUnit_price(result.getString("unit_price"));
	            prod.setProduct_quantity(result.getString("product_quantity"));
	            prod.setProduct_availability(result.getString("product_availability"));
	            prod.setVendor_id(result.getString("vendor_id"));
	            prod.setProduct_id(result.getString("product_id"));
	            prod.setImageUrlFromPart(result.getString("image"));
	            productList.add(prod);  
	        } 
	            // Product with given name and price not found
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace(); // Log the exception for debugging purposes
	    }
	    return productList;
	}
	
	/*
	 * public UserModel getUserByUsername(String username) { try { PreparedStatement
	 * stmt = getConnection()
	 * .prepareStatement("SELECT * FROM user_info WHERE username = ?");
	 * stmt.setString(1, username); ResultSet result = stmt.executeQuery();
	 * 
	 * if (result.next()) { UserModel usr = new UserModel();
	 * usr.setFirstName(result.getString("first_name"));
	 * usr.setLastName(result.getString("last_name"));
	 * usr.setDob(result.getDate("dob").toLocalDate());
	 * usr.setGender(result.getString("gender"));
	 * usr.setPhoneNumber(result.getString("phone_number"));
	 * usr.setEmail(result.getString("email_address"));
	 * usr.setAddress(result.getString("address"));
	 * usr.setImageUrlFromPart(result.getString("user_image")); return usr; } else {
	 * return null; // Product with given ID not found } } catch (SQLException |
	 * ClassNotFoundException ex) { ex.printStackTrace(); return null; } }
	 */

	public UserProfile getUserByUsername(String username) {
	    UserProfile user = null;
	    Connection conn = null;
	    PreparedStatement stmt = null;
	    ResultSet rs = null;

	    try {
	        // Connect to the database
	        conn = getConnection();

	        // Prepare SQL statement to retrieve user data
	        String sql = "SELECT * FROM user_info WHERE username = ?";
	        stmt = conn.prepareStatement(sql);
	        stmt.setString(1, username);

	        // Execute query
	        rs = stmt.executeQuery();

	        // Process the result set
	        if (rs.next()) {
	            // Create a UserProfile object and populate it with data from the result set
	            user = new UserProfile();
	            user.setFirstName(rs.getString("first_name")); // Adjust column name here
	            user.setLastName(rs.getString("last_name"));
	            user.setUsername(rs.getString("username"));
	            user.setDob(rs.getDate("dob").toLocalDate());
	            user.setGender(rs.getString("gender"));
	            user.setPhoneNumber(rs.getString("phone_number"));
	            user.setEmail(rs.getString("email_address"));
	            user.setAddress(rs.getString("address"));
	            user.setUserImage(rs.getString("user_image")); // Adjust column name here
	            // Set other user attributes similarly
	        }
	    } catch (SQLException | ClassNotFoundException ex) {
	        ex.printStackTrace();
	    } finally {
	        // Close the resources in a finally block to ensure they're always closed
	        try {
	            if (rs != null) {
	                rs.close();
	            }
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (conn != null) {
	                conn.close();
	            }
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }
	    return user;
	}
}


