package com.example.clinicapp.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.clinicapp.Clinics.Admin;
import com.example.clinicapp.Clinics.Clients;
import com.example.clinicapp.Clinics.Clinic;
import com.example.clinicapp.Clinics.Employee;

import com.example.clinicapp.Clinics.Service;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

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
        String createClientTable = "create table Client(" + "userName varchar(50)," + "password varchar(50)," + "name varchar(50)," + "age int," + "appointment," + "primary key(userName))";
        db.execSQL(createClientTable);
        String createEmployeeTable = "create table Employee(" + "userName varchar(50)," + "password varchar(50)," + "name varchar(50)," + "address varchar(50)," + "phoneNum long," + "nameOfClinic varchar(50)," + "insuranceTypes varchar(50)," + "paymentMethod varchar(50)," + "workingHour," + "numOfAppointments int," + "primary key(UserName))";
        db.execSQL(createEmployeeTable);
        String createAdminTable = "create table Admin(" + "userName varchar(50)," + "password varchar(50)," + "name varchar(50)," + "primary key(userName))";
        db.execSQL(createAdminTable);
        String createClinicTable = "create table Clinic(" + "name varchar(500)," + "address varchar(500)," + "rate double," + "service," + "workingHour," + "primary key(name))";
        db.execSQL(createClinicTable);

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

        values = new ContentValues();
        values.put("name", "Kanata");
        values.put("address", "kanata");
        db.insert("Clinic", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlC = "delete table if exists Client";
        String sqlE = "delete table if exists Employee";
        String sqlA = "delete table if exists Admin";
        String sqlS = "delete table if exists Service";
        String sqlCl = "delete table if exists Clinic";
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
        Cursor cursor = database.rawQuery("select service, role, person from Service where person = " + "\"" + name + "\"", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String s = cursor.getString(0);
                result.add(s);
                cursor.moveToNext();
            }
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

    public List<Clients> showAllClients(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select userName, name, age from Client", null);
        List<Clients> result = new ArrayList<>();
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                String s = "userName: " + cursor.getString(0) + " name: " + cursor.getString(1) + " age: " + cursor.getString(2);
                result.add(getClient(cursor.getString(0)));
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

    public Clients getClient(String userName){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select userName, password, name, age from Client where userName = \"" + userName + "\"", null);
        cursor.moveToFirst();
        Clients client = new Clients();
        if (cursor.moveToFirst()) {
            client.setUserName(cursor.getString(0));
            client.setPassword(cursor.getString(1));
            client.setName(cursor.getString(2));
            client.setAge(cursor.getInt(3));
        }
        else {
            client = null;
        }
        cursor.close();
        database.close();
        return client;
    }

    public boolean addAppointment(String userNameClient, String time, String userNameEmployee){
        Employee employee = getEmployee(userNameEmployee);
        if(employee == null){
            return false;
        }
        ArrayList<String> appointment = new ArrayList<>();
        appointment.add(time);
        appointment.add(employee.getName());
        appointment.add(employee.getNameOfClinic());

        Gson gson = new Gson();
        String a = gson.toJson(appointment);
        update("Client", "userName", userNameClient, "appointment", a);
        return true;
    }

    public void deleteAppointment(String userNameClient){
        updateToNull("Client", "userName", userNameClient, "appointment");
    }

    public ArrayList<String> getAppointment(String userName){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select appointment from Client where userName = \"" + userName +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        if(cursor.getString(0) == null){
            cursor.close();
            database.close();
            return null;
        }
        else{
            Type type = new TypeToken<ArrayList<String>>() {}.getType();
            ArrayList<String> outputString = gson.fromJson(cursor.getString(0), type);
            cursor.close();
            database.close();
            return outputString;
        }
    }

    public int ApproximateWaitingTime(String date){
        int result = 0;
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select userName from Client";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                ArrayList<String> appointment = getAppointment(cursor.getString(0));
                if(appointment != null){
                    if(appointment.get(0).equals(date)) {
                        result += 15;
                    }
                }

                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return result;
    }



    //Employee Functions
    public boolean checkIfAvailable(String date){
        int DateInt = StringToInt(date);
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select userName from Employee";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                ArrayList<ArrayList> WH = getWorkingHours(cursor.getString(0), "1");
                if(WH == null){
                    cursor.close();
                    database.close();
                    return false;
                }
                for(int j = 0; j < WH.size(); j++){
                    ArrayList<String> wh = WH.get(j);
                    int start = StringToInt(wh.get(0));
                    int end = StringToInt(wh.get(1));
                    if(DateInt >= start && DateInt <= end){
                        cursor.close();
                        database.close();
                        return true;
                    }
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        database.close();
        return false;
    }

    public boolean addAppointmentEmployee(String userName){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select numOfAppointments from Employee where userName = \"" + userName + "\"", null);
        cursor.moveToFirst();
        if(cursor.getInt(0) >= 30){
            cursor.close();
            database.close();
            return false;
        }
        else{
            int to = cursor.getInt(0) + 1;
            updateNumOfAppointment(userName, to);
            cursor.close();
            database.close();
            return true;
        }
    }

    public void deleteAppointmentEmployee(String userName){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select numOfAppointments from Employee where userName = \"" + userName + "\"", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0){
            int num = cursor.getInt(0) - 1;
            updateNumOfAppointment(userName, num);
            cursor.close();
            database.close();
        }
    }

    public List<Employee> showAllEmployees(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select userName, name from Employee", null);
        List<Employee> r = new ArrayList<>();
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                r.add(getEmployee(cursor.getString(0)));
                cursor.moveToNext();
            }
        }
        else{
            r = null;
        }
        cursor.close();
        database.close();
        return r;
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

    public boolean addWorkingHour(String workingHourStart, String workingHourEnd, String userName){
        ArrayList<String> workingHourPeriod = new ArrayList<>();
        workingHourPeriod.add(workingHourStart);
        workingHourPeriod.add(workingHourEnd);

        ArrayList<ArrayList> workingHours = new ArrayList<>();
        String wS;

        SQLiteDatabase database = getReadableDatabase();
        String sql = "select workingHour from Employee where userName=\"" + userName +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        if(cursor.getString(0) == null){
            workingHours.add(workingHourPeriod);
            wS= gson.toJson(workingHours);
            update("Employee", "userName", userName, "workingHour", wS);
        }
        else{
            Type type = new TypeToken<ArrayList<ArrayList>>() {}.getType();
            ArrayList<ArrayList> outputString = gson.fromJson(cursor.getString(0), type);
            workingHours = outputString;
            if(workingHours.contains(workingHourPeriod)){
                return false;
            }
            else{
                workingHours.add(workingHourPeriod);
                wS= gson.toJson(workingHours);
                update("Employee", "userName", userName, "workingHour", wS);
            }
        }
        cursor.close();
        database.close();
        return true;
    }

    public boolean deleteWorkingHour(String workingHourStart, String workingHourEnd, String userName){
        ArrayList<String> workingHours = new ArrayList<>();
        workingHours.add(workingHourStart);
        workingHours.add(workingHourEnd);
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
            Type type = new TypeToken<ArrayList<ArrayList>>() {}.getType();
            ArrayList<ArrayList> outputString = gson.fromJson(cursor.getString(0), type);
            for(int i = 0; i < outputString.size(); i++){
                if(outputString.get(i).get(0).equals(workingHourStart) && outputString.get(i).get(1).equals(workingHourEnd)){
                    outputString.remove(i);
                    if(outputString.size() == 0){
                        updateToNull("Employee", "userName", userName, "workingHour");
                    }
                    else{
                        wS= gson.toJson(outputString);
                        update("Employee", "userName", userName, "workingHour", wS);
                    }
                    cursor.close();
                    database.close();
                    return true;
                }
            }
        }
        cursor.close();
        database.close();
        return false;
    }

    public ArrayList<String> getWorkingHours(String userName){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select workingHour from Employee where userName=\"" + userName +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ArrayList>>() {}.getType();
        ArrayList<ArrayList> output = gson.fromJson(cursor.getString(0), type);
        ArrayList<String> result = new ArrayList<>();
        if (output == null){
            return null;
        }
        for(int i = 0; i < output.size(); i++){
            String start = output.get(i).get(0).toString();
            String end = output.get(i).get(1).toString();
            String workingHour = toTimeFormat(start) + " to " + toTimeFormat(end);
            result.add(workingHour);
        }
        cursor.close();
        database.close();
        return result;
    }

    private ArrayList<ArrayList> getWorkingHours(String userName, String useless){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select workingHour from Employee where userName=\"" + userName +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ArrayList>>() {}.getType();
        if(cursor.getCount() > 0) {
            ArrayList<ArrayList> output = gson.fromJson(cursor.getString(0), type);
            cursor.close();
            database.close();
            return output;
        }
        else{
            cursor.close();
            database.close();
            return null;
        }
    }

    public ArrayList<Service> getServiceOfEmployee(String userName){
        ArrayList<Service> serviceOfEmployee = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select service, role, person from Service where person = \"" + userName +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();

        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                Service service = new Service();
                service.setName(cursor.getString(0));
                service.setRole(cursor.getString(1));
                Employee employee = getEmployee(cursor.getString(2));
                service.setEmployee(employee);
                serviceOfEmployee.add(service);
                cursor.moveToNext();
            }
        }
        else{
            serviceOfEmployee = null;
        }
        return serviceOfEmployee;
    }

    public boolean serviceExistEmployee(String userName, String service){
        ArrayList<Service> s = getServiceOfEmployee(userName);
        if(s == null){
            return false;
        }
        for (int i = 0; i < s.size(); i++){
            if(s.get(i).getName().equals(service)){
                return true;
            }
        }
        return false;
    }

    //Clinic Functions
    public Clinic getClinic(String name){
        Clinic clinic = new Clinic();
        clinic.setServices(getServiceOfClinic(name));
        clinic.setWorkingHour(getClinicWorkingHour(name));
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select name, address, rate from Clinic where name = \"" + name + "\"", null);
        if(cursor.moveToFirst()){
            clinic.setName(cursor.getString(0));
            clinic.setAddress(cursor.getString(1));
            clinic.setRate(cursor.getDouble(2));
        }
        cursor.close();
        database.close();
        return clinic;
    }

    public ArrayList<Clinic> showAllClinics(){
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select name, address, service, rate from Clinic", null);
        ArrayList<Clinic> result = new ArrayList<>();
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<Service>>() {}.getType();
                ArrayList<Service> s = gson.fromJson(cursor.getString(2), type);
                Clinic clinic = new Clinic();
                clinic.setName(cursor.getString(0));
                clinic.setAddress(cursor.getString(1));
                clinic.setRate(cursor.getDouble(3));
                result.add(clinic);
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

    public void insertClinic(Clinic clinic){
        ArrayList<Service> services = clinic.getServices();
        Gson gson = new Gson();
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", clinic.getName());
        values.put("address", clinic.getAddress());
        values.put("rate", clinic.getRate());
        values.put("service", gson.toJson(services));
        database.insert("Clinic", null, values);
        database.close();
    }

    public boolean clinicExist(String name){
        SQLiteDatabase db = getReadableDatabase();
        String sql = "select name from Clinic where name = \"" + name +"\"";
        Cursor cursor = db.rawQuery(sql, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }

    public ArrayList<Service> getServiceOfClinic(String nameOfClinic){
        ArrayList<Service> services = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select service from Clinic where name = \"" + nameOfClinic + "\"", null);
        if(cursor.moveToFirst()) {
            Gson gson = new Gson();
            Type type = new TypeToken<ArrayList<Service>>() {}.getType();
            services = gson.fromJson(cursor.getString(0), type);
        }
        else{
            services = null;
        }
        cursor.close();
        database.close();
        return services;
    }

    public void addService2Clinic(String nameOfClinic, String userName){
        ArrayList<Service> serviceExist = getServiceOfClinic(nameOfClinic);
        ArrayList<Service> serviceOfEmployee = getServiceOfEmployee(userName);

        if(serviceOfEmployee != null && serviceExist != null) {
            for (int i = 0; i < serviceOfEmployee.size(); i++) {
                if (!serviceExist.contains(serviceOfEmployee.get(i))) {
                    serviceExist.add(serviceOfEmployee.get(i));
                }
            }
        }
        else if(serviceExist == null){
            serviceExist = new ArrayList<>();
            for(int i = 0; i < serviceOfEmployee.size(); i++){
                serviceExist.add(serviceOfEmployee.get(i));
            }
        }
        Gson gson = new Gson();
        String service = gson.toJson(serviceExist);
        for(int i =0 ; i < serviceExist.size(); i++){
            System.out.println(serviceExist.get(i).getName());
        }
        for(int i =0 ; i < serviceOfEmployee.size(); i++){
            System.out.println(serviceOfEmployee.get(i).getName());
        }
        update("Clinic", "name", nameOfClinic, "service", service);
    }

    public void deleteServiceClinic(String nameOfClinic, String delete){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select service from Clinic where name = \"" + nameOfClinic +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        String s;
        if(cursor.getString(0) == null){
            cursor.close();
            database.close();
            return;
        }
        else{
            Type type = new TypeToken<ArrayList<Service>>() {}.getType();
            ArrayList<Service> existService = gson.fromJson(cursor.getString(0), type);
            for(int i = 0; i < existService.size(); i++){
                if(existService.contains(delete)){
                    existService.remove(delete);
                }
            }
            if(existService.size() == 0){
                updateToNull("Clinic", "name", nameOfClinic, "service");
            }
            else {
                s = gson.toJson(existService);
                update("Clinic", "name", nameOfClinic, "workingHour", s);
            }
        }
        cursor.close();
        database.close();
    }

    public ArrayList<String> ByAddress(String address){
        ArrayList<String> clinics = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select name from Clinic where address = \"" + address + "\"", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                clinics.add(cursor.getString(0));
                cursor.moveToNext();
            }
        }
        else{
            clinics = null;
        }
        if(clinics.size() == 0){
            clinics = null;
        }
        cursor.close();
        database.close();
        return clinics;
    }

    public ArrayList<String> ByService(String service){
        ArrayList<String> clinics = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select name from Clinic", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                ArrayList<Service> services = getServiceOfClinic(cursor.getString(0));
                if(services != null) {
                    for (int j = 0; j < services.size(); j++) {
                        if (services.get(j).getName().equals(service) && !clinics.contains(cursor.getString(0))) {
                            clinics.add(cursor.getString(0));
                        }
                    }
                }
                cursor.moveToNext();
            }
        }
        else{
            clinics = null;
        }
        if(clinics.size() == 0){
            clinics = null;
        }
        cursor.close();
        database.close();
        return clinics;
    }

    public ArrayList<String> ByWorkingHour(String workingHour){
        int target = StringToInt(workingHour);
        ArrayList<String> employees = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("select userName from Employee", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            for (int i = 0; i < cursor.getCount(); i++) {
                ArrayList<ArrayList> workingHours = getWorkingHours(cursor.getString(0), "1");
                if(workingHours != null) {
                    for (int j = 0; j < workingHours.size(); j++) {
                        int start = StringToInt(workingHours.get(i).get(0).toString());
                        int end = StringToInt(workingHours.get(i).get(1).toString());
                        if (start <= target && end >= target) {
                            Employee employee = getEmployee(cursor.getString(0));
                            employees.add(employee.getName() + " of " + employee.getNameOfClinic());
                        }
                    }
                }
                cursor.moveToNext();
            }
        }
        else{
            employees = null;
        }
        if(employees.size() == 0){
            employees = null;
        }
        cursor.close();
        database.close();
        return employees;
    }

    public void addWorkingHour2Clinic(String userName, String nameOfClinic){
        ArrayList<ArrayList> workingHourEmployee = getWorkingHours(userName, "1");
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select workingHour from Clinic where name = \"" + nameOfClinic +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        String wS;
        if(cursor.getString(0) == null){
            wS= gson.toJson(workingHourEmployee);
            update("Clinic", "name", nameOfClinic, "workingHour", wS);
        }
        else{
            Type type = new TypeToken<ArrayList<ArrayList>>() {}.getType();
            ArrayList<ArrayList> workingHourClinic = gson.fromJson(cursor.getString(0), type);
            for(int i = 0; i < workingHourEmployee.size(); i++){
                if(!workingHourClinic.contains(workingHourEmployee.get(i))){
                    workingHourClinic.add(workingHourEmployee.get(i));
                }
            }
            wS= gson.toJson(workingHourClinic);
            update("Clinic", "name", nameOfClinic, "workingHour", wS);
        }
        cursor.close();
        database.close();
    }

    public void deleteWorkingHourClinic(String nameOfClinic, ArrayList<String> delete){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select workingHour from Clinic where name = \"" + nameOfClinic +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        String wS;
        if(cursor.getString(0) == null){
            cursor.close();
            database.close();
            return;
        }
        else{
            Type type = new TypeToken<ArrayList<ArrayList>>() {}.getType();
            ArrayList<ArrayList> workingHourClinic = gson.fromJson(cursor.getString(0), type);
            for(int i = 0; i < workingHourClinic.size(); i++){
                if(workingHourClinic.contains(delete)){
                    workingHourClinic.remove(delete);
                }
            }
            if(workingHourClinic.size() == 0){
                updateToNull("Clinic", "name", nameOfClinic, "workingHour");
            }
            else {
                wS = gson.toJson(workingHourClinic);
                update("Clinic", "name", nameOfClinic, "workingHour", wS);
            }
        }
        cursor.close();
        database.close();
    }

    public void editWorkingHourClinic(String userName, String nameOfClinic, ArrayList<String> delete){
        deleteWorkingHourClinic(nameOfClinic, delete);
        addWorkingHour2Clinic(userName, nameOfClinic);
    }

    public ArrayList<ArrayList> getClinicWorkingHour(String nameOfClinic){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select workingHour from Clinic where name = \"" + nameOfClinic +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        Gson gson = new Gson();
        Type type = new TypeToken<ArrayList<ArrayList>>() {}.getType();
        ArrayList<ArrayList> result = gson.fromJson(cursor.getString(0), type);
        cursor.close();
        database.close();
        return result;
    }

    public void rate(String nameOfClinic, double rate){
        double r = getRate(nameOfClinic);
        if(r == 0){
            updateRate(nameOfClinic, rate);
        }
        else{
            r += rate;
            r = r/2;
            updateRate(nameOfClinic, r);
        }
    }

    public double getRate(String nameOfClinic){
        SQLiteDatabase database = getReadableDatabase();
        String sql = "select rate from Clinic where name = \"" + nameOfClinic +"\"";
        Cursor cursor = database.rawQuery(sql, null);
        cursor.moveToFirst();
        double rate = 0;
        if (cursor.moveToFirst()) {
            rate = cursor.getDouble(0);
        }
        cursor.close();
        database.close();
        return rate;
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

    public void updateRate(String nameOfClinic, double rate){
        String updateStr = "update Clinic set rate = " + "\'" + rate + "\'" + " where name = \'" + nameOfClinic + "\'";
        getWritableDatabase().execSQL(updateStr);
    }

    public void updateNumOfAppointment(String userName, int to){
        String updateStr = "update Employee set numOfAppointments = " + "\'" + to + "\'" + " where userName = \'" + userName + "\'";
        getWritableDatabase().execSQL(updateStr);
    }

    private int StringToInt(String time){
        return Integer.parseInt(time);
    }

    private long StringToLong(String time){
        return Long.parseLong(time);
    }

    private String intToString(int time){
        return String.valueOf(time);
    }

    private String toTimeFormat(String before){
        StringBuilder to = new StringBuilder(before);
        to.insert(4, "/");
        to.insert(7, "/");
        return to.toString();
    }
}