package com.codesroots.osamaomar.grz.datalayer.localdata.product.deo;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.codesroots.osamaomar.grz.datalayer.localdata.product.entities.ProductDB;
import java.util.List;
import io.reactivex.Single;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface ProductDao {

    @Insert(onConflict = REPLACE)
    public Long insertProduct(ProductDB product);

    @Update
    public void upateBody(ProductDB product);

    @Query("delete from ProductDB where product_id = :productid ")
    public int deleteProduct(int productid);


    @Query("select * from ProductDB")
    public Single<List<ProductDB>> getproductids();

    @Query("delete from  ProductDB")
    public void deleteall();

    @Query("select count(*) from  ProductDB where product_id = :productid ")
    public int checkifexists(int productid);

}
