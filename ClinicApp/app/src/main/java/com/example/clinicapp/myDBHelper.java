package com.example.clinicapp;
import android.content.ContentValues;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.database.Cursor;

public class myDBHelper extends SQLiteOpenHelper
{
    private static final int VERSION = 1;
    private static final String NameOfDataBase = "ClinicDataBase.db";

    public myDBHelper(Context context)
    {
        super(context,NameOfDataBase,null, VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onCreate(db);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

        String ClientTable="create table Client("+"userName varchar(25),"+"password varchar(25),"+"name varchar(25),"+"age int,"+"primary key(userName))";
        db.execSQL(ClientTable);

        String EmployeeTable="create table Employee("+"userName varchar(25),"+"password varchar(25),"+"name varchar(25),"+"primary key(UserName))";
        db.execSQL(EmployeeTable);

        String AdminTable="create table Admin("+"userName varchar(25),"+"password varchar(25),"+"name varchar(25),"+"primary key(userName))";
        db.execSQL(AdminTable);

        String newPassword=encrypt("5T5ptQ");
        ContentValues theValues=new ContentValues();

        theValues.put("userName","admin");
        theValues.put("password",newPassword);
        theValues.put("name","admin");

        db.insert("Admin",null,theValues);
    }

    public void remove(String table,String uName)
    {
        SQLiteDatabase theDatabase=getWritableDatabase();

        String removeElement="DELETE FROM "+table+" WHERE userName = \""+uName+"\"";
        theDatabase.execSQL(removeElement);
        theDatabase.close();

    }

    public String[] showE()
    {
        SQLiteDatabase database=getReadableDatabase();

        Cursor cursor=database.rawQuery("select userName, name from Employee",null);

        String[] result=new String[cursor.getCount()];
        cursor.moveToFirst();

        if(cursor.getCount() > 0)
        {
            for (int i = 0; i < cursor.getCount(); i++)
            {
                String s ="userName: "+cursor.getString(0)+" name: "+cursor.getString(1);

                result[i] = s;
                cursor.moveToNext();
            }
        }
        else{
            result=null;
        }

        cursor.close();
        database.close();

        return result;
    }

    public String[] showC()
    {
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select userName, name, age from Client",null);

        String[] result = new String[cursor.getCount()];
        cursor.moveToFirst();

        if(cursor.getCount()>0)
        {
            for (int i=0; i < cursor.getCount(); i++) {
                String s="userName: "+cursor.getString(0)+" name: "+cursor.getString(1)+" age: "+cursor.getString(2);

                result[i]=s;
                cursor.moveToNext();
            }
        }
        else{
            result = null;
        }

        cursor.close();
        database.close();

        return result;
    }

    public void insertClient(Clients client)
    {
        SQLiteDatabase theDatabase=getWritableDatabase();
        ContentValues theValues=new ContentValues();

        theValues.put("userName",client.getUserName());
        String encrypted=encrypt(client.getPassword());

        theValues.put("password",encrypted);
        theValues.put("name",client.getName());
        theValues.put("age",client.getAge());

        theDatabase.insert("Client",null,theValues);
        theDatabase.close();
    }

    public void insertEmployee(Employee employee){

        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();

        String encrypted = encrypt(employee.getPassword());

        values.put("password", encrypted);
        values.put("name", employee.getName());
        values.put("userName", employee.getUserName());


        database.insert("Employee", null, values);
        database.close();
    }

    public Clients clientExist(String userName, String password)
    {
        SQLiteDatabase db=getReadableDatabase();

        String new_password=encrypt(password);

        String sql="select userName, password, name, age from Client where userName = \"" + userName +"\"" + "and password = \"" + new_password + "\"";

        Cursor cursor=db.rawQuery(sql, null);
        Clients client=new Clients();

        if (cursor.moveToFirst()) {
            client.setUserName(cursor.getString(0));
            client.setPassword(cursor.getString(1));
            client.setName(cursor.getString(2));
            client.setAge(cursor.getInt(3)) ;
        }
        else {
            client = null;
        }

        cursor.close();
        db.close();
        return client;
    }

    public boolean cExist(String userName)
    {
        SQLiteDatabase db=getReadableDatabase();

        String sql = "select userName, password, name, age from Client where userName = \""+userName+"\"";
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean eExist(String userName)
    {

        SQLiteDatabase db=getReadableDatabase();

        String sql="select userName, password, name from Employee where userName = \""+userName+"\"";
        Cursor cursor=db.rawQuery(sql, null);

        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }


    public Admin adminExist(String userName, String password)
    {
        String p=encrypt("5T5ptQ");
        Admin admin=new Admin();

        String new_password=encrypt(password);

        if (userName.equals(admin.getUserName()) && new_password.equals(p))
        {
            return admin;
        }
        else{
            return null;
        }
    }

    public Employee employeeExist(String userName, String password){

        SQLiteDatabase db = getReadableDatabase();
        String new_password = encrypt(password);

        String sql = "select userName, password, name from Employee where userName=\""+userName+"\""+"and password = \""+new_password+"\"";
        Cursor cursor = db.rawQuery(sql, null);
        Employee employee = new Employee();

        if (cursor.moveToFirst())
        {
            employee.setUserName(cursor.getString(0));
            employee.setPassword(cursor.getString(1));
            employee.setName(cursor.getString(2));
        }
        else {
            employee = null;
        }
        cursor.close();
        db.close();
        return employee;
    }

    private String encrypt(String password)
    {

        byte data[] = null;

        MessageDigest m;

        try {

            data=password.getBytes("UTF8");
            m=MessageDigest.getInstance("SHA-256");

            m.update(data);
            byte resultData[] = m.digest();

            return convertToHexString(resultData);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String convertToHexString(byte data[]) {

        StringBuffer stringBuffer = new StringBuffer();

        for (int i = 0; i < data.length; i++) {
            stringBuffer.append(Integer.toHexString(0xff & data[i]));
        }

        return stringBuffer.toString();
    }
}