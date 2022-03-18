# 4장 예외

---

# 절대 해서는 안되는 예외처리 방법들

- 예외를 잡아서 아무런 조취를 취하지 않거나 의미 없는 `throws` 선언을 남발하는 것은 위험하다.

## 예외를 잡고도 아무것도 하지 않는다.

```java
try {
	...
} catfch (SQLException e) {
	// 예외를 잡고 아무것도 하지 않음.
}
```

    * 예외 밣생을 무시해버리고 정상적인 상황인 것처럼 다음 라인으로 넘어가겠다는 분명한 의도가 있는 게 아니라면 연습중에도 *절대 만들어서는 안되는 코드다.*

- 프로그램 실행 중에 어디선가 오류가 있어서 예외가 발생했는데 그것을 무시하고 계속 진행해버리게 된다. 결국 발생한 예외로 인해 예상치 못한 다른 문제를 일으키게 된다.
- 더 큰 문제는 그 시스템 오류나 이상한 결과의 원인이 무엇인지 찾아내기가 매우 어려워진다.

## 예외 메시지를 화면 출력만 한다.

```java
try {
	...
} catfch (SQLException e) {
	// 화면 출력만 함 1.
	System.out.pirntln(e);
	// 화면 출력만 함 2.
	e.printStackTrace();
}
```

- 단순히 메시지를 출력한것은 예외를 처리한것이 아니다.
  - 콘솔이나 로그에 예외 메시지를 출력하는 건 아무런 도움이 되지 않는다.

## 무책임한 throws

```java
public void method1() throws Exception {
	method2();
	...
}

public void method2() throws Exception {
	method3();
	...
}

public void method3() throws Exception {
	...
}
```

- 정확하게 예외 이름을 적어서 선언하는게 중요하다
  - 적절한 처리를 통해 복구될 수 있는 예외상황도 제대로 다룰수 있는 기회를 박탈당한다.

---

# 예외의 종류와 특징

## Error

- `java.lang.Error`클래스의 서브클래스들
- 에러는 시스템에 뭔가 비정상적인 상황이 발생했을 경우에 사용된다.
- 주로 JVM에서 발생시키는것이고 애플리케이션 코드에서 잡으려고 하면 안된다.
  - `OutOfMemoryError`나 `ThreadDeath`같은 에러는 `catch`블록으로 잡아봤자 아무런 대응 방법이 없기 때문이다.
- 애플리케이션에서는 이런 에러에 대한 처리는 신경 쓰지 않아도 된다.

## Exception과 체크 예외(Checked Exception)

[image:8C87C710-E77A-4CF1-8750-7E7CE9AA25F1-51408-000018D015901469/Screen Shot 2022-03-18 at 10.00.28.png]

- `java.lang.Exception`클래스와 그 서브클래스로 정의되는 예외들.
- 개발자들이 만든 애플리케이션 코드의 작업중에 예외상황이 발생했을 경우 사용된다.
- `Exception`은 `Checked Exception`과 `UnChecked Exception`으로 구분된다.
- 일반적으로 예외라고 하면 체크예외라고 생각해도 된다.

### 체크 예외 (Checked Exception)

- `Exception`의 서브클래스이면서 `RuntimeException`클래스를 상속하지 않은것
- 체크예외의 경우 반드시 예외를 처리하는 코드를 작성 해줘야 한다.
  - `catch`로 잡던지 `throws`를 정의해서 던저야 한다. 그렇지 않으면 *컴파일 에러*가 발생한다.
- 체크 예외는 복구할 가능성이 조금이라도 있는 말그대로 예외적인 상황이기 때문에 자바는 이를 처리하는 `try`, `catch` 블록이나 `throws`선언을 강제한다.

### 언체크 예외 (UnChecked Exception)

- `RuntimeException`클래스를 상속한 클래스들을 말한다.
- 명시적인 예외처리를 강제하지 않기 때문에 언체크 예외라고 불린다. 혹은 런타임 예외라고도 한다.
- 런타임 예외는 주로 프로그램의 오류가 있을 때 발생하도록 의도된 것들이다.
  - 대표적으로 `NullPointerException`, `IllegalArgumentException` 등
- 개발자가 부주의해서 발생할 수 있는 경우에 발생하도록 만든것이 런타임 예외
- 런타임 예외는 예상하지 못했던 예외상황에서 발생하는게 아니기 때문에 굳이 `catch`나 `throws`를 사용하지 않아도 되도록 만든것

---

# 예외처리 방법

- 예외를 처리할 떄 반드시 지켜야 할 핵심 원칙은 모든 예외는 적절하게 복구되든지 아니면 작업을 중단시키고 운영자 또는 개발자에게 분명하게 통보돼야 한다.
- 대응이 발가능한 체크 예외라면 빨리 런타임 예외로 전환해서 던지는게 낫다.
- 예외는 복구하거나 예외처리 오브젝트로 의도적으로 전달하거나 적절한 예외로 전환해야 한다.
- 복구 할 수 없는 예외는 가능한 한 빨리 런타임 예외로 전환하는 것이 바람직하다.
- 애플리케이션의 로직을 담기 위한 예외는 체크 예외로 만든다.

## 예외 복구

- 예외상황을 파악하고 문제를 해결해서 정상 상태로 돌려놓는 방법.
- 예외로 인해 기본 작업 흐름이 불가능하면 다른 작업 흐름으로 자연스럽게 유도해주는 것.

```java
// 재시도를 통한 예외를 복구하는 코드 예제
int maxretry = MAX_RETRY; // 상수 별도 지정
while(maxretry -- > 0) {
	try {
		...			// 예외 발생 가능성이 있는 시도
		return;		// 작업 성공
	} catch(SomeException e) {
		// 로그 출력, 정해진 시간만큼 대기
	} finally {
		// 리소스 반납, 정리 작업
	}
}
// 최대 재시도 횟수를 넘기면 직접 예외 발생
throw new RetryFailedException();
```

## 예외처리 회피

- 예외처리를 자신이 담당하지 않고 자신을 호출한 쪽으로 던저버리는 것.
- `throws`문으로 선언해서 예외가 발생하면 알아서 던저지게 하거나, `catch`문으로 일단 예외를 잡은 후에 로그를 남기고 다시 예외를 던지는(rethrow) 방법.
- 예외처리를 회피하려면 반드시 다른 오브젝트나 메소드가 예외를 대신 처리할 수 있도록 명시적인 예외 이름을 적시하여 던저줘야 한다.
- 예외를 회피하는것은 예외를 복구하는것처럼 의도가 분명해야 한다.

```java
// 예외처리 회피 1
public void add() throws SQLException {
	// JDBC API
}

// 예외처리 회피 2
public void add() throws SQLException {
	try {
		// JDBC API
	} catch (SQLException e) {
		// 로그 출력
		throw e;
	}
}
```

## 예외 전환

- 예외 전환의 사용목적 첫번째는 굳이 필요하지 않은 catch / throws를 줄여주는것
- 두번째는 로우레벨의 예외를 좀더 의미있고 추상화된 예외로 바꿔서 던져주는 것이다.
- 예외 회피와 비슷하게 예외를 복구해서 정상적인 상태로는 만들 수 없기 때문에 예외를 메소드 밖으로 던지는것이다. 하지만 예외 회피와 달리, 발생한 예외를 그대로 넘기는게 아니라 _적절한 예외로 전환해서 던진다._
- 예외 전환의 목적은 의미를 분명하게 해줄 수 있는 예외로 바꿔주기 위해서다
  - `SQLException` -> `DuplicateUserIdException` 의미가 분명한 예외로 전환

```java
// 예외 전환 기능을 가진 DAO
public void add(User user) throws DuplicateUserIdException,
	SQLException {
	try {
		// JDBC를 이용해 user 정로를 DB에 추가하는 코드
		// 또는 SQLException을 던지는 메소드를 호출하는 코드
	} catch (SQLException e) {
		// ErrorCode가 MySQL의 Duplicate Entry(1062)이면 예외 전환
		if(e.getErrorCode() == MySqlErrorNumbers.ER_DUP_ENTRY) {
			throw new DuplicateUserIdException();
		} else {
			throw e; // 그 외의 경우는 SQLException 그대로
		}
	}
}
```

- 보통 전환하는 예외에 원래 발생한 예외를 담아서 중첩 예외(nested exception)로 만드는것이 좋다.
  - 중첩 예외는 `getCause()` 메소드를 이용해서 처음 발생한 예외가 무엇인지 확인 할 수 있다.
  - 중첩 예외는 새로운 예외를 만들면서 생성자나 `initCause()`메소드로 근본 원인이 되는 예외를 넣어주면 된다.

```java
// 중첩 예외 근본 원인 넣어주기 1
catch (SQLException e) {
	...
	throw DuplicateUserIdException(e);
}
// 중첩 예외 근본 원인 넣어주기 2
catch (SQLException e) {
	...
	throw DuplicateUserIdException().initCause(e);
}
```

- 예외 처리를 쉽고 단순하게 만들기 위해 포장하는것
  - 주로 예외처리를 강제하는 체크예외를 언체크 예외인 런타임 예외로 바꾸는 경우에 사용된다.
  - 대표적으로 `EJBException`
    - EJB 컴포넌트 코드에서 발생하는 대부분의 체크예외는 의미있는 예외이거나 복구가능한 예외가 아니다 -> 런타임 예외인 `EJBException`로 포장해서 던진다.

```java
catch(NamingException e) {
	throw new EJBException(e)
}
```

- 스프링은 자바의 다양한 데이터 엑세스 기술을 사용할 때 발생하는 예외들을 추상화해서 `DataAccessException` 계층구조 안에 정리해 놓았다.
  - 사용 기술에 독립적인 일광성 있는 예외를 던질 수 있게 해준다.
- SQLException의 에러코드를 DB별로 매핑해서 그에 해당하는 의미 있는 `DataAccessException`의 서브클래스 중 하나로 전환해서 던져준다.

> _참고 : 낙관적인 락킹(Optimistic locking)_
> JPA, Hibernate같이 오브젝트/엔티티 단위로 정보를 업데이트하는 경우 발생할수 있다.
> 같은 정보를 두 명 이상의 사용자가 동시에 조회하고 순차적으로 업데이트를 할 때, 뒤늦게 업데이트한 것이 먼저 업데이트한 것을 덮어쓰지 않도록 막아주는 데 쓸 수 있는 편리한 기능

---

# 예외처리 전략

## 낙관적인 예외처리 전략

- 어디에서든 예외를 잡아서 처리할 수 있다면 굳이 체크예외로 만들지 않고 런타임 예외로 만드는것이 낫다. 대신 예외를 던지는 메소드는 명시적으로 `throws`선언을 해줘야 한다.
- 런타임 예외를 사용하는 경우에는 API문서나 레퍼런스 문서등을 통해, 메소드를 사용할 떄 발생할 수 있는 예외의 종류와 원인, 활용 방법을 자세히 설명해둬야 한다.

```java
// Exception의 생성
public class DuplicateUserIdException
	extends RuntimeException {
		// 중첩 예외를 만들수 있도록 생성자를 추가
    public DuplicateUserIdException(Throwable cause) {
        super(cause);
    }
}
```

```java
// 예외 전환 기능을 가진 DAO
public void add(User user) throws DuplicateUserIdException {
	try {
		// JDBC를 이용해 user 정로를 DB에 추가하는 코드
		// 또는 SQLException을 던지는 메소드를 호출하는 코드
	} catch (SQLException e) { // 언체크 예외로 포장 throws 선언 불필요
		// ErrorCode가 MySQL의 Duplicate Entry(1062)이면 예외 전환
		if(e.getErrorCode() == MySqlErrorNumbers.ER_DUP_ENTRY) {
			throw new DuplicateUserIdException(e);
		} else {
			throw new RuntimeException(e); // 그 외의 경우는 에외 포장
		}
	}
}
```

## 애플리케이션 예외

- 시스템 또는 외부의 예외 상황이 원인이 아니라 애플리케이션 자체의 로직에 의해 의도적으로 발생시키고, 반드시 `catch` 해서 무엇인가 조치를 취하도록 요구하는 예외

```java
try {
	BigDecimal balance = account.withdraw(amount);
	...
	// 정상적인 처리결과를 출력하도록 진행
} catch(InSufficientBalanceException e) {
	//InSufficientBalanceException에 담긴 인출 가능한 잔고금액 정보를 가져온다
	BigDecimal availFunds = e.getAvailFunds();
	...
	// 잔고 부족 안내 메시지를 준비하고 이를 출력하도록 진행
}
```

- 스프링의 `JdbcTemplate`은 `JdbcTemplate` 템플릿과 콜백 안에서 발생하는 모든 `SQLException`을 런타임 예외인 `DataAccessException`으로 포장해서 던져준다.

---
