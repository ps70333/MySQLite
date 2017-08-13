package tw.com.ps70333.mysqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private MyDBHelper dbHelper;
    private SQLiteDatabase db;
    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbHelper=new MyDBHelper(this,"Simon",null,1);
        db=dbHelper.getReadableDatabase();
        tv=(TextView)findViewById(R.id.tv);
    }
    public void doInsert(View v){
        //建立ContentValues物件
        ContentValues contentValues=new ContentValues();
        contentValues.put("cname","simon");
        contentValues.put("tel","0987654321");
        contentValues.put("birthday","2017-08-13");
        //參數1:表格名稱；參數2:沒有指定欄位值的預設值；參數3:ContentValues物件
        db.insert("cust",null,contentValues);
        Toast.makeText(this,"Insert OK",Toast.LENGTH_SHORT).show();
    }
    public void doUpdate(View v){
        ContentValues contentValues=new ContentValues();
        contentValues.put("cname","Alex");
        contentValues.put("tel","0988123456");
        db.update("cust",contentValues,"_id=? and cname=?",new String[]{"2","simon"});
        Toast.makeText(this,"Update OK",Toast.LENGTH_SHORT).show();
    }
    public void doDelete(View v){
        // delete from cust where _id = 1 and cname = 'simon'
        db.delete("cust","_id=? and cname=?",new String[]{"1","simon"});
        Toast.makeText(this,"Delete OK",Toast.LENGTH_SHORT).show();
    }
    public void doSelect(View v){
        //select * from cust
        Cursor cursor=db.query("cust",null,null,null,null,null,null);
        int count=cursor.getCount();
        StringBuffer sb=new StringBuffer();
        while(cursor.moveToNext()){
            String id=cursor.getString(0);
            String cname=cursor.getString(1);
            String tel=cursor.getString(2);
            String birthday=cursor.getString(3);
            sb.append(id+" : "+cname+" : "+tel+" : "+birthday+"\n");
        }
        tv.setText(sb);
        Toast.makeText(this,"Select OK",Toast.LENGTH_SHORT).show();
    }


}
