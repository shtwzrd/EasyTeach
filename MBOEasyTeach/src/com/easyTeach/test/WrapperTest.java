package com.easyTeach.test;

import com.easyTeach.server.databaseWrapper.ClassWrapper;
import com.easyTeach.server.entity.Class;

public class WrapperTest {

    public static void main(String[] args) {
        Class classEntity = new Class();
        classEntity.setYear(2013);
        classEntity.setClassName("DAT13W");
        
        ClassWrapper classWrapper = new ClassWrapper();
        classWrapper.insertIntoClass(classEntity);
    }
    
}
