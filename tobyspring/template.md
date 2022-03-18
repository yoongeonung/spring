# 3장 템플릿

---

- 템플릿이란 바뀌는 성질이 다른 코드 중에서 변경이 거의 일어나지 않으며 일정한 패턴으로 유지되는 특성을 가진 부분을 자유롭게 변경되는 성질을 가진 부분으로부터 독립시켜서 효과적으로 활용할 수 있도록 하는 방법이다.

> _참고 : 마이크로 DI_
> 의존관계 주입(DI)은 다양한 형태로 적용할 수 있다. *DI의 가장 중요한 개념은 제3자의 도움을 통해 두 오브젝트 사이의 유연한 관계가 설정되도록 만든다는 것*이다. 이 개념만 따른다면 DI를 이루는 오브젝트와 구성요소의 구조나 관계는 다양하게 만들 수 있다.일반적으로 DI는 의존관계에 있는 두 개의 오브젝트와 이 관계를 다이내믹하게 설정해주는 오브젝트 팩토리(DI 컨테이너), 그리고 이를 사용하는 클라이언트라는 4개의 오브젝트 사이에서 일어난다. 하지만 때로는 원시적인 전략 패턴 구조를 따라 클라이언트가 오브젝트 팩토리의 책임을 함께 지고 있을 수도 있다. 또는 클라이언트와 전략(의존 오브젝트)이 결합될 수도 있다. 심지어는 클라이언트와 DI 관계에 있는 두 개의 오브젝트가 모두 하나의 클래스 안에 담길 수도 있다.이런 경우에는 DI가 매우 작은 단위의 코드와 메소드 사이에서 일어나기도 한다. 얼핏 보면 DI 같아 보이지 않지만, 세밀하게 관찰해보면 작은 단위지만 엄연히 DI가 이뤄지고 있음을 알 수 있다. 이렇게 DI의 장점을 단순화해서 IoC 컨테이너의 도움 없이 코드 내에서 적용한 경우를 마이크로 DI라고도 한다. 또는 코드에 의한 DI라는 의미로 수동 DI라고 부를 수도 있다.

---

# 기타

- 일반적으로 서버에서는 제한된 개수의 DB 커넥션을 만들어서 재사용 가능한 풀로 관리한다.
  - DB풀은 매번 `getConnection()`으로 가져간 커넥션을 명시적으로 `close()`해서 돌려줘야지만 다시 풀에 넣었다가 다음 커넥션 요청이 있을 때 재사용 할 수 있다.
  - `close()`는 만들어진 순서의 반대로 하는것이 원칙이다.

---

# 중첩 클래스

- 다른 클래스 내부에 정의되는 클래스를 중첩 클래스(nested class)라고 한다.
- 중첩 클래스는 독립적으로 오브젝트로 만들어질 수 있는 스태틱 클래스(static class)와 자신이 정의된 클래스의 오브젝트 안에서만 만들어질 수 있는 내부 클래스(inner class)로 구분된다.
- 내부 클래스는 다시 범위(scope)에 따라 세 가지로 구분된다.
  - 멤버 필드처럼 오브젝트 레벨에 정의 되는 멤버 내부 클래스(member inner class)
  - 메소드 레벨에 정의되는 로컬 클래스(local class)
  - 이름을 갖지 않는 익명 내부 클래스(anonymous inner class)다. 익명 내부 클래스의 범위는 선언된 위치에 따라서 다르다.

---

## 로컬 클래스

- 메소드 레벨에 정의되는 로컬 클래스(local class)
- 로컬 클래스는 선언된 메소드 내에서만 사용할 수 있다.
- 자신이 선언된 곳의 정보에 접근할수 있다.
  - 내부 클래스에서 외부의 변수를 사용할 때는 외부 변수는 반드시 final로 선언해줘야 한다.

```java
public void add(final User user) throws SQLException {
	class AddStatement implements StatementStrategy {
		...
		user.getId();
		...
	}
	StatementStrategy strategy = new AddStatement();
	...
}
```

---

## 익명 내부 클래스

- 익명 내부 클래스(anonymous inner class)는 이름을 갖지 않는 클래스다. 클래스 선언과 오브젝트 생성이 결합된 형태로 만들어지며, 상속할 클래스나 구현할 인터페이스를 생성자 대신 사용해서 다음과 같은 형태로 만들어 사용한다. 클래스를 재사용할 필요가 없고, 구현한 인터페이스 타입으로만 사용할 경우에 유용하다.
- `new 인터페이스이름() { 클래스 본문 };`

---

# 템플릿 / 콜백 패턴

- 전략 패턴의 기본 구조에 익명 내부 클래스를 활용한 방식을 스프링에서는 탬플릿/콜백 패턴이라고 한다.
- 단일 전략 메소드를 갖는 전략패턴이면서 익명 내부 클래스를 사용해서 매번 전략을 새로 만들어 사용하고, 컨텍스트 호출과 동시에 전략 DI를 수행하는 방식을 템플릿 / 콜백 패턴이라고 한다.
- 전략패턴의 컨텍스트를 템플릿이라고 부르고, 익명 내부 클래스로 만들어지는 오브젝트를 콜백이라고 부른다.
  - 콜백의 코드에도 일정한 패턴이 반복된다면 콜백을 탬플릿에 넣고 재활용하는 것이 편리하다.
- 탬플릿과 콜백의 타입이 다양하게 바뀔수 있다면 제네릭스를 이용한다.
- 템플릿은 한번에 하나 이상의 콜백을 사용할 수도 있고, 하나의 콜백을 여러번 호출할 수도 있다.
- 템플릿 / 콜백을 설계할 떄는 템플릿과 콜백 사이에 주고받는 정보에 관심을 둬야한다.

> _참고 : 템플릿_
> 템플릿(template)은 어떤 목적을 위해 미리 만들어둔 모양이 있는 틀을 가리킨다. 학생들이 도형을 그릴 때 사용하는 도형자 또는 모양자가 바로 템플릿이다. 프로그래밍에서는 고정된 틀 안에 바꿀 수있는 부분을 넣어서 사용하는 경우에 템플릿이라고 부른다. JSP는 HTML이라는 고정된 부분에 EL 과 스크립릿이라는 변하는 부분을 넣은 일종의 템플릿 파일이다. 템플릿 메소드 패턴은 고정된 틀의 로직을 가진 템플릿 메소드를 슈퍼클래스에 두고, 바뀌는 부분을 서브클래스의 메소드에 두는 구조로 이뤄진다.

> _참고 : 콜백_
> 콜백(callback)은 실행되는 것을 목적으로 다른 오브젝트의 메소드에 전달되는 오브젝트를 말한다.파라미터로 전달되지만 값을 참조하기 위한 것이 아니라 특정 로직을 담은 메소드를 실행시키기 위해 사용한다. 자바에선 메소드 자체를 파라미터로 전달할 방법은 없기 때문에 메소드가 담긴 오브젝트를 전달해야 한다. 그래서 펑셔널 오브젝트(functional object)라고도 한다.

## 탬플릿 / 콜백의 특징

[image:F3905663-DDB0-4689-9C02-B7C13081974E-51408-0000155447E4D8B3/Screen Shot 2022-03-15 at 21.01.09.png]

- 탬플릿 / 콜백 패턴의 콜백은 보통 단일 메소드 인터페이스를 사용한다.
- 클라이언트의 역할은 탬플릿 안에서 실행될 로직을 담은 콜백 오브젝트를 만들고, 콜백이 참조할 정보를 제공하는것이다. 만들어진 콜백은 클라이언트가 탬플릿의 메소드를 호출 할 때 파라미터로 전달된다.
- 탬플릿은 정해진 작업 흐름을 따라 작업을 진행하다가 내부에서 생성한 참조정보를 가지고 콜백 오브젝트의 메소드를 호출한다. 콜백은 클라이언트 메소드에 있는 정보와 탬플릿이 제공한 참조정보를 이용해서 작업을 수행하고 그 결과를 다시 탬플릿에 돌려준다.
- 탬플릿은 콜백이 돌려준 정보를 사용해서 작업을 마저 수행한다. 경우에 따라 최종 결과를 클라이언트에 다시 돌려주기도 한다.
- 콜백 오브젝트가 내부 클래스로서 자신을 생성한 클라이언트 메소드 내의 정보를 직접 참조한다.

---

# 스프링의 Jdbc Template

- 스프링이 제공하는 다양한 탬플릿 / 콜백 패턴
- JDBC와 같은 예외가 발생할 가능성이 있으며 공유 리소스의 반환이 필요한 코드는 반드시 `try/catch/finally` 블록으로 관리해야 한다.

## update()

- 템플릿으로부터 `Connection`을 제공받아서 `PreparedStatement`를 만들어 돌려준다

```java
// 탬플릿 메소드 update()와 콜백 new PreparedStatementCreator()
this.jdbcTemplate.update(new PreparedStatementCreator() {
    @Override
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        return con.prepareStatement("delete from USER");
    }
});
```

- SQL문장만 전달하면 미리 준비된 콜백을 만들어서 탬플릿을 호출하는 기능

```java
// 같은 탬플릿 메소드 update()이지만 파라미터가 틀리다.
// jdbcTemplate의 내장 콜백함수를 사용
jdbcTemplate.update("delete from USER");
```

- 치환자를 가진 SQL로 `PreparedStatement`를 만들고 함께 제공하는 파라미터를 순서대로 바인딩해주는 기능을 가진 `update()` 메소드

```java
jdbcTemplate.update(
	"insert into USER (id, name, password) values (?,?,?)",
			 user.getId(), user.getName(), user.getPassword());
```

---

## query()

- 여러개의 로우결과로 나오는 일반적인 경우에 사용된다.
- `query()`의 리턴타입은 `List<T>`다.
- `query()`는 제네릭 메소드로 타입은 파라미터로 넘기는 `RowMapper<T>` 콜백 오브젝트에서 결정된다.
- `query()`템플릿은 SQL을 실행해서 얻은 `ResultSet`의 모든 로우를 열람하면서 로우마다 `RowMapper`콜백을 호출한다. -> SQL쿼리를 실행해 DB에서 가져오는 로우의 개수만큼 콜백이 호출된다.
- SQL Query를 실행하고 ResultSet을 통해 결과값을 가져오는 방법
  - 이런 작업 흐름에 사용할수 있는 탬플릿은 `PreparedStatementCreator` 콜백과 `ResultSetExtractor` 콜백을 파라미터로 받는 `query()` 메소드.

```java
// jdbcTemplate 1, queryForInt() 는 deprecated 됨.
jdbcTemplate.query(new PreparedStatementCreator() {
    @Override
    public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
        return con.prepareStatement
						("select COUNT(*) from USER");
    }
}, new ResultSetExtractor<Integer>() {
    @Override
    public Integer extractData(ResultSet rs) throws SQLException, DataAccessException {
        rs.next();
        return rs.getInt(1); // 1부터 시작
    }
});
```

---

## queryForObject()

- query의 결과가 row 하나일때 사용된다.
- `ResultSetExtractor`와 `RowMapper`
  - 둘다 모두 탬플릿으로부터 `ResultSet`을 전달받고, 필요한 정보를 추출해서 리턴하는 방식으로 동작
- `ResultSetExtractor`는 `ResultSet`을 한 번 전달받아 알아서 추출 작업을 모두 진행하고 최종결과만 리턴.
- `RowMapper`는 `ResultSet`의 로우 하나를 매핑하기 위해 사용되기 때문에 여러번 호출될 수 있다.

```java
// jdbcTemplate 적용
// sql, rowMapper, args가 들어간다.
return jdbcTemplate.queryForObject(
	"select * from USER where id = ?",
	new RowMapper<User>() {
    @Override
    public User mapRow(ResultSet rs, int rowNum)
										throws SQLException {
        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));
        return user;
    }
},
	id
);

// queryForInt()는 deprecated
jdbcTemplate.queryForObject
				("select COUNT(*) from USER", Integer.class);
```

---
