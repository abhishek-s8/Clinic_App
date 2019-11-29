package com.example.clinicapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.clinicapp.Clinics.Admin;
import com.example.clinicapp.Clinics.Clients;
import com.example.clinicapp.Clinics.Employee;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class DataBase extends SQLiteOpenHelper {
    private static final String NAMEofDataBase = "ClinicDataBase.db";
    private static final int VERSION = 1;

    public DataBase(Context context){
        super(context, NAMEofDataBase, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createServiceTable = "create table Service(" + "service varchar(50)," + "role varchar(50)," + "person varchar(50)," + "primary key(service))";
        db.execSQL(createServiceTable);
        String createClientTable = "create table Client(" + "userName varchar(50)," + "password varchar(50)," + "name varchar(50)," + "age int," + "primary key(userName))";
        db.execSQL(createClientTable);
        String createEmployeeTable = "create table Employee(" + "userName varchar(50)," + "password varchar(50)," + "name varchar(50)," + "address varchar(50)," + "phoneNum long," + "nameOfClinic varchar(50)," + "insuranceTypes varchar(50)," + "paymentMethod varchar(50)," + "workingHour," + "primary key(UserName))";
        db.execSQL(createEmployeeTable);
        String createAdminTable = "create table Admin(" + "userName varchar(50)," + "password varchar(50)," + "name varchar(50)," + "primary key(userName))";
        db.execSQL(createAdminTable);

        String new_password = encrypt("5T5ptQ");
        ContentValues values = new ContentValues();
        values.put("userName", "admin");
        values.put("password", new_password);
        values.put("name", "admin");
        db.insert("Admin", null, values);

        values = new ContentValues();
        values.put("service", "Injection");
        values.put("role", " by nurse");
        db.insert("Service", null, values);
        values = new ContentValues();
        values.put("service", "Sell medicine");
        values.put("role", " by staff");
        db.insert("Service", null, values);
        values = new ContentValues();
        values.put("service", "Infusion");
        values.put("role", " by nurse");
        db.insert("Service", null, values);
        values = new ContentValues();
        values.put("service", "Tooth extraction");
        values.put("role", " by doctor");
        db.insert("Service", null, values);
        values = new ContentValues();
        values.put("service", "Optometry");
        values.put("role", " by staff");
        db.insert("Service", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlC = "delete table if exists Client";
        String sqlE = "delete table if exists Employee";
        String sqlA = "delete table if exists Admin";
        String sqlS = "delete table if exists Service";
        onCreate(db);
    }

    //Service Functions
    public boolean serviceExist(String service){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select service, role from Service where service = \"" + service +"\"";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            cursor.close();
            db.close();
            return true;
        }
        else {
            cursor.close();
            db.close();
            return false;
        }
    }

    public boolean addService(String s, String r){
        if(serviceExist(s)){
            return false;
        }
        else{
            SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("service", s);
            values.put("role", r);
            database.insert("Service", null, values);
            database.close();
            return true;
        }
    }

    public boolean addService(String s, String r, String p){
        if(serviceExist(s)){
            return false;
        }
        else{
            SQLiteDatabase database = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("service", s);
            values.put("role", r);
            values.put("person", p);
            database.insert("Service", null, values);
            database.close();
            return true;
        }
    }

    public boolean deleteService(String s){
        if(serviceExist(s)){
            SQLiteDatabase database = getWritableDatabase();
            String removeElement = "DELETE FROM Service  WHERE service = \"" + s + "\"";
            database.execSQL(removeElement);
            database.close();
            return true;
        }
        else{
            return false;
        }
    }

    public boolean editService(String service, String new_service, String r){
        return this.deleteService(service) && this.addService(new_service, r);
    }

    public String[] showServices(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select service, role from Service", null);
        String[] result = new String[cursor.getCount()];
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String s = cursor.getString(0) + cursor.getString(1);
                result[i] = s;
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

    public ArrayList<String> showService(String name){
        ArrayList<String> result = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select service, role, person from Service", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                if(cursor.getString(2) == null){
                    cursor.moveToNext();
                    break;
                }
                if(cursor.getString(2).equals(name)) {
                    String s = cursor.getString(0);
                    result.add(s);
                }
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

    //Client Functions
    public void insertClient(Clients client){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("userName", client.getUserName());
        String encrypted = encrypt(client.getPassword());
        values.put("password", encrypted);
        values.put("name", client.getName());
        values.put("age", client.getAge());
        database.insert("Client", null, values);
        database.close();
    }

    public String[] showAllClients(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select userName, name, age from Client", null);
        String[] result = new String[cursor.getCount()];
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String s = "userName: " + cursor.getString(0) + " name: " + cursor.getString(1) + " age: " + cursor.getString(2);
                result[i] = s;
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

    public Clients clientExist(String userName, String password){
        SQLiteDatabase db = getReadableDatabase();
        String new_password = encrypt(password);
        String sql = "select userName, password, name, age from Client where userName = \"" + userName +"\"" + "and password = \"" + new_password + "\"";
        Cursor cursor = db.rawQuery(sql, null);
        Clients client = new Clients();
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

    public boolean cExist(String userName){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select userName, password, name, age from Client where userName = \"" + userName +"\"";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    //Employee Functions
    public String[] showAllEmployees(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select userName, name from Employee", null);
        String[] result = new String[cursor.getCount()];
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String s = "userName: " + cursor.getString(0) + " name: " + cursor.getString(1);
                result[i] = s;
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

    public void insertEmployee(Employee employee){
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        String encrypted = encrypt(employee.getPassword());
        values.put("userName", employee.getUserName());
        values.put("password", encrypted);
        values.put("name", employee.getName());
        values.put("address", employee.getAddress());
        values.put("phoneNum", employee.getPhoneNum());
        values.put("nameOfClinic", employee.getNameOfClinic());
        values.put("insuranceTypes", employee.getInsuranceTypes());
        values.put("paymentMethod", employee.getPaymentMethod());
        database.insert("Employee", null, values);
        database.close();
    }

    public Employee employeeExist(String userName, String password){
        SQLiteDatabase db = getReadableDatabase();
        String new_password = encrypt(password);
        String sql = "select userName, password, name from Employee where userName=\"" + userName +"\"" + "and password = \"" + new_password + "\"";
        Cursor cursor = db.rawQuery(sql, null);
        Employee employee = new Employee();
        if (cursor.moveToFirst()) {
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

    public boolean eExist(String userName){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select userName, password, name from Employee where userName = \"" + userName +"\"";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public Employee getEmployee(String userName){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select userName, password, name, address, phoneNum, nameOfClinic, insuranceTypes, paymentMethod from Employee where userName = \"" + userName + "\"", null);
        cursor.moveToFirst();
        Employee employee = new Employee();
        if (cursor.moveToFirst()) {
            employee.setUserName(cursor.getString(0));
            employee.setPassword(cursor.getString(1));
            employee.setName(cursor.getString(2));
            employee.setAddress(cursor.getString(3));
            employee.setPhoneNum(cursor.getLong(4));
            employee.setNameOfClinic(cursor.getString(5));
            employee.setInsuranceTypes(cursor.getString(6));
            employee.setPaymentMethod(cursor.getString(7));
        }
        else {
            employee = null;
        }
        cursor.close();
        database.close();
        return employee;
    }

    public boolean addWorkingHour(String workingHour, String userName){
        ArrayList<String> workingHours = new ArrayList<>();
        String wS;
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select workingHour from Employee where userName=\"" + userName +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        if(cursor.getString(0) == null){
            workingHours.add(workingHour);
            wS= gson.toJson(workingHours);
            update("Employee", "userName", userName, "workingHour", wS);
        }
        else{
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> outputString = gson.fromJson(cursor.getString(0), type);
            workingHours = outputString;
            if(workingHours.contains(workingHour)){
                return false;
            }
            else{
                workingHours.add(workingHour);
                wS= gson.toJson(workingHours);
                update("Employee", "userName", userName, "workingHour", wS);
            }
        }
        cursor.close();
        database.close();
        return true;
    }

    public boolean deleteWorkingHour(String workingHour, String userName){
        ArrayList<String> workingHours = new ArrayList<>();
        String wS;
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select workingHour from Employee where userName=\"" + userName +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        if(cursor.getString(0) == null){
            return false;
        }
        else{
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> outputString = gson.fromJson(cursor.getString(0), type);
            workingHours = outputString;
            boolean success = workingHours.contains(workingHour);
            if(success){
                workingHours.remove(workingHour);
                if(workingHours.isEmpty()){
                    updateToNull("Employee", "userName", userName, "workingHour");
                }
                else{
                    wS= gson.toJson(workingHours);
                    update("Employee", "userName", userName, "workingHour", wS);
                }
            }
            else{
                return false;
            }
        }
        cursor.close();
        database.close();
        return true;
    }

    public ArrayList<String> getWorkingHours(String userName){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select workingHour from Employee where userName=\"" + userName +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<String>>() {}.getType();
        ArrayList<String> result = gson.fromJson(cursor.getString(0), type);
        cursor.close();
        database.close();
        return result;
    }

    //Admin Functions
    public Admin adminExist(String userName, String password){
        String p = encrypt("5T5ptQ");
        Admin admin = new Admin();
        String new_password = encrypt(password);
        if (userName.equals(admin.getUserName()) && new_password.equals(p)){
            return admin;
        }
        else{
            return null;
        }
    }

    //Public Functions
    public void remove(String table, String uName){
        SQLiteDatabase database = getWritableDatabase();
        String removeElement = "DELETE FROM " + table + " WHERE userName = \"" + uName + "\"";
        database.execSQL(removeElement);
        database.close();
    }

    private String convertToHexString(byte data[]) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            stringBuffer.append(Integer.toHexString(0xff & data[i]));
        }
        return stringBuffer.toString();
    }

    private String encrypt(String password) {
        byte data[] = null;
        MessageDigest m;
        try {
            data = password.getBytes("UTF8");
            m = MessageDigest.getInstance("SHA-256");
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

    public void update(String table, String primaryKeyIs, String primaryKey, String which, String s){
        String updateStr = "update " + table + " set " + which + " = " + "\'" + s + "\' where " + primaryKeyIs + " = \'" + primaryKey + "\'";
        getWritableDatabase().execSQL(updateStr);
    }

    private void updateToNull(String table, String primaryKeyIs, String primaryKey, String which){
        String updateStr = "update " + table + " set " + which + " = NULL " + " where " + primaryKeyIs + " = \'" + primaryKey + "\'";
        getWritableDatabase().execSQL(updateStr);
    }

    public void updatePhoneNum(String primaryKey, long num){
        String updateStr = "update Employee set phoneNum = " + "\'" + num + "\'" + " where userName = \'" + primaryKey + "\'";
        getWritableDatabase().execSQL(updateStr);
    }
}