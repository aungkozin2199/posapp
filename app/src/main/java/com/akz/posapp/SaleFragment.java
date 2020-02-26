package com.akz.posapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.akz.posapp.adapter.RcAdapter;
import com.akz.posapp.adapter.SaleDetAdapter;
import com.akz.posapp.dao.ItemDAO;
import com.akz.posapp.dao.OrderDAO;
import com.akz.posapp.dao.SaleItemDAO;
import com.akz.posapp.model.ItemModel;
import com.akz.posapp.model.OrderModel;
import com.akz.posapp.model.SaleItemModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class SaleFragment extends Fragment {
    Button btnsave;
OrderDAO orderDAO;
Button orderNo;
ItemDAO itemDAO;
static TextView txttotal;
static Context context;
static RecyclerView recyclerView,rcSaleItem;

    public SaleFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_sale, container, false);
        context =  getContext();
        orderDAO =new OrderDAO(getContext());
        orderNo=v.findViewById(R.id.ordernumber);

        itemDAO=new ItemDAO(getContext());
        recyclerView=v.findViewById(R.id.saleitemlist);
        rcSaleItem=v.findViewById(R.id.saledet);
        btnsave=v.findViewById(R.id.btnsave);
        txttotal=v.findViewById(R.id.ordertotal);

        ArrayList<ItemModel> itemModels=itemDAO.getModels();
        RcAdapter adapter=new RcAdapter(itemModels.toArray(),getContext(),getResources().getString(R.string.item_frag));
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

        getNewVoucherNo();
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderDAO orderDAO=new OrderDAO(getContext());
                OrderModel model=new OrderModel();
                model.CustomerId=1;
                model.OrderDate=new SimpleDateFormat("ddMMyyyy").format(new Date());
                model.OrderNo=orderNo.getText().toString();
                model.Sr=orderDAO.getCount(new SimpleDateFormat("ddMMyyyy").format(new Date()))+1;
                model.Total=total;
               long id= orderDAO.saveModel(model);
                SaleItemDAO saleItemDAO=new SaleItemDAO(getContext());

            for (int i=0;i<saleItemModels.size();i++){
                saleItemModels.get(i).OrderId=(int)id;
                saleItemDAO.saveModel(saleItemModels.get(i));
            }

            String header="<!DOCTYPE html>\n" +
                    "<html>\n" +
                    "<head>\n" +
                    "<style>\n" +
                    "table, th, td {\n" +
                    "  border: 1px solid black;\n" +
                    "  border-collapse: collapse;\n" +
                    "}\n" +
                    "</style>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "<table style=\"width:100%\">\n" +
                    "  <tr>\n" +
                    "    <th>No</th>\n" +
                    "    <th>Item</th> \n" +
                    "    <th>Qty</th>\n" +
                    "    <th>Total</th>\n" ;
        String footer= "</table>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n";
        StringBuilder body=new StringBuilder();
        ItemDAO dao=new ItemDAO(getContext());
        for (int i=0;i<saleItemModels.size();i++){
            SaleItemModel temp=saleItemModels.get(i);
            ItemModel model1=dao.getModelById(temp.ItemId);
            body.append("<tr>");
            body.append("<td>"+temp.Sr+"</td>");
            body.append("<td>"+model1.Name+"</td>");
            body.append("<td"+temp.Qty+"</td>");
            body.append("<td>"+temp.Qty*temp.SPrice+"</td>");
            body.append("</tr>");
        }
        body.append("<tr><td colspan='3'>Total</td><td>"+total+"</td>");
                String html=header+body+footer;
        print(html);
                saleItemModels.clear();
                total=0;
                txttotal.setText("");
                loadItem();
                getNewVoucherNo();
            }
        });
        return  v;
    }
    public static void loadItem(){
        SaleDetAdapter adapter=new SaleDetAdapter(saleItemModels,context);
        rcSaleItem.setAdapter(adapter);
        rcSaleItem.setLayoutManager(new LinearLayoutManager(context,RecyclerView.VERTICAL,false));
        txttotal.setText(total+"");
    }

public void getNewVoucherNo(){
    Date mydate=new Date();
    SimpleDateFormat sm=new SimpleDateFormat("ddMMyyyy");
    String currentDate=sm.format(mydate);
    int count=orderDAO.getCount(currentDate);
    orderNo.setText(currentDate+(count+1));
}
public void print(String html){
    WebView webView=new WebView(getContext());
    webView.loadDataWithBaseURL(null,html,"text/html","utf-8",null);
    webView.setWebViewClient(new WebViewClient(){
        @Override
        public void onPageFinished(WebView view, String url) {
            createWebPrintJob(view);
        }
    });
}
static int total=0;
public  static ArrayList<SaleItemModel>saleItemModels=new ArrayList<SaleItemModel>();
public static void insertSaleItem(ItemModel model){
    int index=-1;
    for (int i=0;i<saleItemModels.size();i++){
        if (saleItemModels.get(i).ItemId==model.Id){
            index=i;
            saleItemModels.get(i).Qty+=1;
            total+=saleItemModels.get(i).SPrice;
                    break;
        }
    }
if (index==-1){
    SaleItemModel saleItemModel=new SaleItemModel();
    saleItemModel.Sr=saleItemModels.size()+1;
    saleItemModel.ItemId=model.Id;
    saleItemModel.Qty=1;
    saleItemModel.SPrice=model.SalePrice;
    saleItemModel.OPrice=model.OriginalPrice;
    saleItemModels.add(saleItemModel);
    total+=model.SalePrice;
}
loadItem();
}
    private void createWebPrintJob(WebView webView) {

        // Get a PrintManager instance
        PrintManager printManager = (PrintManager) getActivity()
                .getSystemService(Context.PRINT_SERVICE);

        String jobName = orderNo.getText().toString() + " Document";

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter(jobName);
        PrintAttributes.Builder attributes=new PrintAttributes.Builder();
        attributes.setMediaSize(PrintAttributes.MediaSize.ISO_A4);

        // Create a print job with name and adapter instance
        PrintJob printJob = printManager.print(jobName, printAdapter,attributes.build());



    }

}
