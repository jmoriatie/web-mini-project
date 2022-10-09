package com.api.miniproject.util.page;

import com.api.miniproject.domain.Item;

import java.util.ArrayList;
import java.util.List;

public class PageResolver {

    public static int[] getPageNumberingArray(int itemsListSize){
        int pageCount = itemsListSize % 10 == 0? itemsListSize/10 : itemsListSize/10+1; // 10단위일때 한페이지 더생김
        int[] pages = new int[pageCount];
        for(int i=0; i<pageCount; i++){
            pages[i] = i+1;
        }
        return pages;
    }

    public static List<Item> setSinglePageItemList(Integer page, List<Item> items) {

        int maxCnt = page * 10; // 마지막
        int minCnt = maxCnt - 10;

        List<Item> itemListInPage = new ArrayList<>();
        int itemsCount = items.size();

        for(int i=minCnt; i<maxCnt; i++){
            if(i < itemsCount) {
                itemListInPage.add(items.get(i));
            }else break;
        }
        return itemListInPage;
    }
}
