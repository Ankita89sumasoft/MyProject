package Utilities;

public class Routes {
    public static String Base_URL="https://petstore.swagger.io/v2";

//user
    public static String Post_URL=Base_URL+"/user";
    public static String Get_URL=Base_URL+"/user/{username}";
    public static String Update_URL=Base_URL+"/user/{username}";
    public static String delete_URL=Base_URL+"/user/{username}";

//pet
    public static String Post_PetURL=Base_URL+"/pet";
    public static String Get_PetURL=Base_URL+"/pet/{petId}";
    public static String Update_PetURL=Base_URL+"/pet";
    public static String Delete_PetURL=Base_URL+"/pet/{petId}";


}
