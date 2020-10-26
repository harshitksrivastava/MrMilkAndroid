package com.example.mrmilk.databases;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Single;

public class LocalCartDataSource implements CartDataSource{

    private CartDAO cartDAO;

    public LocalCartDataSource(CartDAO cartDAO) {
        this.cartDAO = cartDAO;
    }


    @Override
    public Flowable<List<CartItem>> getAllCart(String userPhone) {
        return cartDAO.getAllCart(userPhone);
    }

    @Override
    public Single<Integer> countItemInCart(String userPhone) {
        return cartDAO.countItemInCart(userPhone);
    }

    @Override
    public Single<Long> sumPrice(String userPhone) {
        return cartDAO.sumPrice(userPhone) ;
    }

    @Override
    public Single<CartItem> getItemInCart(String foodId, String userPhone) {
        return cartDAO.getItemInCart(foodId,userPhone);
    }

    @Override
    public Completable insertOrReplaceAll(CartItem cart) {
        return cartDAO.insertOrReplaceAll(cart);
    }

    @Override
    public Single<Integer> updateCart(CartItem cart) {
        return cartDAO.updateCart(cart);
    }

    @Override
    public Single<Integer> deleteCart(CartItem cart) {
        return cartDAO.deleteCart(cart);
    }

    @Override
    public Single<Integer> cleanCart(String userPhone) {
        return cartDAO.cleanCart(userPhone);
    }
}
