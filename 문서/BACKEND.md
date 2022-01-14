# 백엔드 학습내용

<!-- 필수 항목 -->

JPA란

: OR mapping 즉 객체와 DB간의 매핑을 위한 프레임워크이다.

SQL문을 직접 사용하는것이 아닌 객체를 사용한다.

인터페이스 덩어리, Hibernate가 구현체로 사용된다.

JPA를 사용함으로써 SQL 중심적인 개발에서 객체 중심으로 개발이 가능해졌다.

객체 답게 모델링을 진행 할 수록 매핑 작업이 많아진다.

객체를 자바 컬렉션에 저장하고 불러오듯이 db에 저장할 수 있는 방법이 JPA이다.

JPA 동작 핵심 객체
1. Persistence
: JPA 시작 객체로서 persistence.xml을 읽어서 DB 접속 정보 등 설정 정보를 수집한다.
2. EntityManagerFactory
: Persistence로부터 DB 접속 정보등을 얻어서 Coonection Pool등 매우 비싼 자원을 구성한다.
: 애플리케이션에서 딱 하나만 생성하면 되고 Thread safe 하기 때문에 여러 쓰레드에서 공유할 수 있다.
: createEntityManager()을 통해 EntityManager을 생성한다.
3. EntityManager
: Entity에 관한 CRUD를 처리하는 핵심 클래스이다.
: Mybatis의 SqlSession과 비슷하게 Connection과 연관되어 있으며 SQL을 처리하고 TX를 관리한다.
: 클라이언트의 요청이 발생할 때마다 비슷한 EntityManager가 동작하며 Thread Safe 하지 않기 때문에 공유해서 사용하면 안된다.
: DML을 처리하기 위해서 트랜잭션 하에서 getTransaction()을 통해 EntityTransation을 얻을 수 있고 이를 통해 commit/callback을 처리한다.

Persistence Context

: Entity가 영구적으로 저장되는 환경으로 EntityMnaager가 managed 상태의 entity를 관리하는 일종의 메모리 DB영역이다.

persistence Context는 크게

1. 1차 캐시 영역
2. 쓰기 지연 SQL 저장소

로 구성된다.

1차 캐시 영역은 entity의 식별자를 key로 enttiy 객체의 레퍼런스를 관리할 수 있는데

추가로 entity의 원본 내용에 해당하는 snapshot 정보도 함께 관리한다.

만약 entity의 내용과 snapshot의 내용이 다른 경우는 update가 필요한 상황으로 인지하게 된다.

쓰기 지연 SQL 저장소는 INSERT , UPDATE, DELETE 쿼리를 DB에 반영하지 않고 저장해두는 영역이다. 여기 저장된 SQL 문장은 트랜잭션이 커밋됐을때 DB에 반영된다.
