package hello.core.singleton;

public class SingletonService {

    // static 영역에 객체를 딱 1개만 생성해둔다.
    // JVM 자바가 뜰 때 내부적으로 실행해서 객체를 생성해서 인스턴스에 참조를 넣어둔다.

    private static final SingletonService instance = new SingletonService();

    // 이 객체 인스턴스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
    public static SingletonService getInstance(){
        return instance;
    }

    // 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
    private SingletonService(){
    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}