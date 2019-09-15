package com.codesroots.osamaomar.grz.datalayer.apidata;

import com.codesroots.osamaomar.grz.models.entities.AddLocation;
import com.codesroots.osamaomar.grz.models.entities.Addmessage;
import com.codesroots.osamaomar.grz.models.entities.ChatList;
import com.codesroots.osamaomar.grz.models.entities.Contact;
import com.codesroots.osamaomar.grz.models.entities.Currency;
import com.codesroots.osamaomar.grz.models.entities.DefaultAdd;
import com.codesroots.osamaomar.grz.models.entities.AddToFavModel;
import com.codesroots.osamaomar.grz.models.entities.CartItems;
import com.codesroots.osamaomar.grz.models.entities.ViewLocation;
import com.codesroots.osamaomar.grz.models.entities.Favoriets;
import com.codesroots.osamaomar.grz.models.entities.LoginResponse;
import com.codesroots.osamaomar.grz.models.entities.MainView;
import com.codesroots.osamaomar.grz.models.entities.MyOrders;
import com.codesroots.osamaomar.grz.models.entities.OrderModel;
import com.codesroots.osamaomar.grz.models.entities.ProductDetails;
import com.codesroots.osamaomar.grz.models.entities.ProductRate;
import com.codesroots.osamaomar.grz.models.entities.Products;
import com.codesroots.osamaomar.grz.models.entities.Register;
import com.codesroots.osamaomar.grz.models.entities.StoreSetting;
import com.codesroots.osamaomar.grz.models.entities.UserLocations;
import com.codesroots.osamaomar.grz.models.entities.offers;

import java.util.ArrayList;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ServerGateway {

    @GET("Products/mainpage.json")
    Observable<MainView> getMainViewData();


    @GET("Products/productdetails/{product_id}.json")
    Observable<ProductDetails> getProductDetails(
            @Path("product_id") int product_id
    );


    @GET("Products/getproductsbycatid/{cat_id}/{page}.json")
    Observable<Products> getProducts(
            @Path("cat_id") int cat_id,
            @Path("page") int page
    );


    @GET("Products/paginateproduct/{page_id}.json")
    Observable<MainView> getProductsPaginationinmainPage(
            @Path("page_id") int cat_id
    );


    @FormUrlEncoded
    @POST("Favourites/addfavourite.json")
    Observable<AddToFavModel> addToFave(
            @Field("user_id") int user_id,
            @Field("product_id") int product_id
    );


    @GET("Favourites/delete/{favid}.json")
    Observable<DefaultAdd> DeleteFav(
            @Path("favid") int favid
    );


    @GET("Favourites/getproductsfav/{userid}.json")
    Observable<Favoriets> getFavProducts(
            @Path("userid") int userid
    );


    @FormUrlEncoded
    @POST("Products/viewproduct.json")
    Observable<CartItems> getCartProducts(
            @Field("arrayofid[]") ArrayList<Integer> ids
    );


    @GET("orders/getuserorder/{userid}.json")
    Observable<MyOrders> retrieveUserOrders(
            @Path("userid") int userid
    );


    @GET("BillingAddress/index/{userid}.json")
    Observable<UserLocations> retrieveUserLocations(
            @Path("userid") int userid
    );


    @GET("contact_us.json")
    Observable<Contact> getContacts();


    @GET("offers/getoffers.json")
    Observable<offers> retrieveOffers();



    @GET("rating/getproductrate/{product_id}.json")
    Observable<ProductRate> getProductRates(
            @Path("product_id") int product_id
    );


    @FormUrlEncoded
    @POST("rating/addrate.json")
    Observable<DefaultAdd> addProductRate(
            @Field("customer_id") int user_id,
            @Field("product_id") int product_id,
            @Field("rated") int rate,
            @Field("feedback") String comment,
            @Field("status") int status
    );


    @FormUrlEncoded
    @POST("Chatting/addchat.json")
    Observable<Addmessage> addmessageChat(
            @Field("customer_id") int user_id,
            @Field("sender") int address,
            @Field("message_text") String message_text
    );

    @FormUrlEncoded
    @POST("BillingAddress/add.json")
    Observable<AddLocation> addBillingAddress(
            @Field("customer_id") int user_id,
            @Field("first_name") String first_name,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("state_country") String state_country,
            @Field("town_city") String town_city,
            @Field("notes") String notes
    );


    @FormUrlEncoded
    @POST("BillingAddress/edit/{locationid}.json")
    Observable<AddLocation> editBillingAddress(
            @Path("locationid") int locationid,
            @Field("first_name") String first_name,
            @Field("phone") String phone,
            @Field("address") String address,
            @Field("state_country") String state_country,
            @Field("town_city") String town_city,
            @Field("notes") String notes
    );


    @FormUrlEncoded
    @POST("Orders/edit/{orderid}.json")
    Observable<AddLocation> editOrderStatues(
            @Path("orderid") int orderid,
            @Field("order_status") int order_status
    );




    @GET("BillingAddress/view/{billingid}.json")
    Observable<ViewLocation> viewLocation(
            @Path("billingid") int billingid
    );



    @FormUrlEncoded
    @POST("Chatting/getuserchat.json")
    Observable<ChatList> getChatList(
            @Field("customer_id") int user_id
    );


    ////////////// make order
    @POST("orders/addorder.json")
    @Headers("Accept: Application/json")
    Call<ResponseBody> makeOrder(
            @Body OrderModel orderModel
    );


    ////////////// get  Settings
    @GET("Storesettings.json")
    @Headers("Accept: Application/json")
    Observable<StoreSetting> getStorSetting();

    ////////////// get  search results
    @FormUrlEncoded
    @POST("products/searchbyname/{type}.json")
    Observable<Products> getSearchResult(
            @Field("name") String name,
            @Path("type") String type
    );

    ////////////// get currency
    @GET("currency/index.json")
    @Headers("Accept: Application/json")
    Observable<Currency> Currency();


    @FormUrlEncoded
    @POST("users/add.json")
    Observable<Register> userregister(
            @Field("customer_email") String username,
            @Field("customer_password") String customer_password,
            @Field("customer_contact") String customer_contact,
            @Field("status") int email_verified
    );

    @FormUrlEncoded
    @POST("users/token.json")
    Observable<LoginResponse> userlogin(
            @Field("customer_email") String username,
            @Field("customer_password") String password
    );

}
