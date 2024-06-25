package com.ohgiraffers.section02.reflection;

import java.lang.reflect.*;

public class Application {

    public static void main(String[] args) throws InvocationTargetException, InstantiationException, IllegalAccessException {

        // reflect란?
        // 컴파일된 자바 코드에서 역으로 클래스를 불러 메소드 및 필드 정보를 구해오는 방법을 제공한다.
        // 반사, 투영 이라는 의미를 가진다.
        // 스프링, 마이바이트, 하이버네이트 등의 라이브러리에서 사용한다.

        // 주의사항
        // 1. 오버헤드 발생 : 성능 저하를 발생할 수 있기 때문에 성능에 민감한 애플리케이션은 사용하지 않는다.
        // 2. 캡슐화 저해 : private 로 설정한 member에 접근 가능하기 때문에 코드 기능이 저하되며 여러가지 문제를 야기시킨다.

        Class class1 = Account.class;
        System.out.println("class1 = " + class1);

        Class class2 = new Account().getClass();
        System.out.println("class2 = " + class2);

        try {
            //동적 로딩 (런타임 시 로딩)
            Class class3 = Class.forName("com.ohgiraffers.section02.reflection.Account");
            System.out.println("class3 = " + class3);

            Class class4 = Class.forName("[D");
            System.out.println("class4 = " + class4);

            Class class5 = double[].class;
            System.out.println("class5 = " + class5);

            Class class6 = Class.forName("[Ljava.lang.String;");
            System.out.println("class6 = " + class6);

            Class class7 = String[].class;
            System.out.println("class7 = " + class7);

            Class superClass = class1.getSuperclass();
            System.out.println("superClass = " + superClass);


        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        // 필드 정보 반환
        Field[] fields = Account.class.getDeclaredFields();
        for(Field field : fields) {
            System.out.println("modifiers = " + Modifier.toString(field.getModifiers()));
            System.out.println("type = " + field.getType());
            System.out.println("name = " + field.getName());
        }

        //생성자 정보 확인
        Constructor[] constructors = Account.class.getDeclaredConstructors();
        for(Constructor constructor : constructors) {
            System.out.println("name : " + constructor.getName());
            Class[] params = constructor.getParameterTypes();
            for(Class param : params) {
                System.out.println("paramType = " + param.getTypeName());
            }
        }

        try {
            Account acc = (Account) constructors[0].newInstance("20", "110-123-456789", "1234", 10000);
            System.out.println(acc.getBalance());

        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        // 메소드 정보에 접근
        Method[] methods = Account.class.getMethods();
        Method getBalanceMethod = null;
        for(Method method : methods) {
            System.out.println(Modifier.toString(method.getModifiers())+" "
                    +method.getReturnType()+" "
                    +method.getName());

            method.getName().equals("getBalance");//nullPointerException이 발생할 가능성이 있다.
            if("getBalance".equals(method.getName())) {
                getBalanceMethod = method;
            }
        }
        System.out.println(getBalanceMethod.invoke(((Account)constructors[2].newInstance())));
    }
}