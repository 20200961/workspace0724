--1 테이블 모든 정보 조회
select * from JOB;

--2. JOB 테이블의 직급 이름만 조회
select JOB_NAME from JOB;

--3. DEPARTMENT 테이블의 모든 정보 조회
select * from department;

--4. employee 테이블의 직원명, 이메일, 전화번호, 고용일 조회
select EMP_NAME,EMAIL,PHONE,HIRE_DATE from employee;

--5. employee 테이블의 이름,연봉,총수령액(보너스 포함),실수령액(총수령액 -(연봉*세금(3%))) 
select EMP_NAME 이름,SALARY*12 연봉,((SALARY+(SALARY*NVL(BONUS,0)))*12) 총수령액,((SALARY+(SALARY*NVL(BONUS,0)))*12)*0.97 실수령액 from employee;

-- 데이터베이스에서 NULL은 빈값을 의미한다.
-- 모든 연산에 NULL이 포함된 경우, 결과는 NULL이 된다

/*
    테이블
    - 데이터 베이스에서 데이터를 저장하는 기본 개념
    - 행과 열로 구성된 데이터 집합
    
    컬럼
    - 테이블 내의 각 데이터 속성을 정의하는 필드
    - 컬럼은 테이블에 저장할 때 속성 = 값으로 저장
    
    => 테이블은 여러 컬럼으로 구성되고, 각 컬럼은 테이블이 표현하는 데이터의 세부적인 속성을 나타냄
    
    
    <select>
    SELECT 컬럼명1, 컬럼명2, ...
    FROM 테이블명
    [WHERE 조건]
    [ORDER BY 정렬기준 [ASC | DESC]]
*/

-- 사원명, 입사일, 근무일수를 EMPLOYEE테이블에서 조회
select EMP_NAME 사원명, HIRE_DATE 입사일 from employee;

-- 데이터베이스에서 날짜를 계산할때 덧셈, 뺄셈이 가능하다
-- 현재시간 - 입사일 = 근무시간
-- DATE - DATE => 결과를 무조건 일로 표시

-- 코드 실행시 현재 날짜를 표시하는 상수 : SYSDATE[년/월/일/시/분/초]
select EMP_NAME,HIRE_DATE,(SYSDATE - HIRE_DATE) * 24 from employee;

-- dual 테이블은 오라클에서 제공하는 가상 테이블
-- 현재 날짜 출력
select SYSDATE from dual;

/* 
    <컬럼 별칭>
    컬럼명에 별칭을 부여하면 깔끔하게 표현 가능
    [표현식]
    컬럼명 별칭 / 컬럼명 AS 별칭 / 컬럼명 "별칭" / 컬럼명 AS "별칭"
*/

select EMP_NAME 사원명, SALARY AS 급여, BONUS "보너스", SALARY * 12 AS "연봉"
from employee;


/* 
    리터널
    직접 값을 나타내는 단위, 임의로 지정한 값
*/

select EMP_ID, EMP_NAME, SALARY, '원'
from employee;

/* 
    <연결 연산자 : ||>
    여러 컬럼값들을 마치 하나의 컬럼처럼 연결할 수 있음
*/

select EMP_NAME || '님 급여는' || SALARY || '원'
from employee;


/* 
    <DISTINCT>
    중복제거 - 컬럼에 표시된 값들을 한번씩만 조회하고자 사용
    한 명령어에서 한번만 사용 가능
    항상 ROW 데이터 전체에 대해서 중복 제거
*/


-- 실제로 사용되고 있는 직급 목록
select distinct JOB_CODE
from employee;

-- 실제로 사용되고 있는 부서 목록
select distinct DEPT_CODE
from employee;

-- 밑처럼 사용시 (JOB_CODE, DEPT_CODE)를 쌍으로 묶어서 중복 제거
select distinct JOB_CODE, DEPT_CODE
from employee;

===============================================================================

/*
    <Where 절>
    조회하고자 하는 테이블로부터 특정 조건에 만족하는 데이터만 조회하고자 할 때 사용
    조건식에서도 다양한 연산자를 사용
    
    [표현법]
    select 컬럼, 컬럼 ...
    from 테이블명 
    where 조건;
    
    >> 비교연산자 <<
    >, <, >=, <= : 대소 비교
    !=, ^=, <> : 양쪽이 다르다
    
*/

-- Employee 테이블에서 부서코드가 D9인 사원들만 조회(모든 컬럼)
select * from employee
where DEPT_CODE = 'D9';

-- Employee 테이블에서 부서코드가 D9인 사원들의 사원명, 급여, 부서코드 조회(모든 컬럼)
select EMP_NAME, SALARY, DEPT_CODE from employee
where DEPT_CODE = 'D1';

-- Employee 테이블에서 부서코드가 D1이 아닌 사원들의 사원명, 급여, 부서코드 조회(모든 컬럼)
select EMP_NAME, SALARY, DEPT_CODE from employee
where DEPT_CODE != 'D1';

-- 월급이 400만원 이상인 사원의 사원명, 부서코드, 급여 조회
select EMP_NAME, DEPT_CODE, SALARY from employee
where SALARY >= 4000000;


/*
    AND, OR 연산자
    조건을 여러개 연결할때 사용
    [표현법]
    조건 A AND 조건 B -> 조건 A와 조건 B가 모두 만족하는 값을 참으로 한다.
    조건 A OR 조건 B -> 조건 A와 조건 B중 하나만 만족하는 값을 참으로 한다.
    
    <BETWEEN AND>
    조건식에 사용되는 구문
    몇이상 몇이하인 범위에 대한 조건을 제시할 때 주로 사용되는 연산자(이상, 이하만 가능)
    [표현법]
    비교대상 컬럼 BETWEEN 하안값 AND 상안값;
*/

-- 급여가 350만원 이상 600만원 이하인 모든 사원의 사원명, 사번, 급여 조회
select EMP_NAME, EMP_ID, SALARY from employee
-- where SALARY >= 3500000 and SALARY <= 6000000
where SALARY between 3500000 and 6000000;


-- NOT : 논리부정 연산자
-- 컬럼명 앞에 또는 BETWEEN 앞에 선언 가능

select EMP_NAME, EMP_ID, SALARY from employee
where not SALARY between 3500000 and 6000000;


-- 입사일이 '90/01/01' 이상 '01/01/01' 이하인 사원들을 전체 조회
select * from employee 
where HIRE_DATE >= '90/01/01' and HIRE_DATE <= '01/01/01';

-- NULL을 비교 연산할 때 =을 사용할 수 없다
select * from employee
where BONUS is NULL;

===============================================================================

/*
    <Like>
    비교하고자 하는 컬럼값이 내가 제시한 특정 패턴에 만족할 경우 조회
    
    [표현법]
    비교한 대상 컬럼 LIKE '특정패턴'; -> 일치하는 것만 조회
    
    특정 패턴을 제시할 때 와일드카드라는 개념 정의되어 있다.
    1. '%' : 포함문자 검색(0글자 이상 전부 조회)
    EX) 비교할 대상 컬럼 LIKE '문자%' : 비교할 대상 컬럼 값 중에 해당문자로 시작하는 값을 전부 조회
        비교할 대상 컬럼 LIKE '%문자' : 비교할 대상 컬럼 값 중에 해당문자로 끝나는 값을 전부 조회
        비교할 대상 컬럼 LIKE '%문자%' : 비교할 대상 컬럼 값 중에 해당문자로 포함된 값을 전부 조회

    2. '_' : 1글자를 대체 검색할 때 사용
    EX) 비교할 대상 컬럼 LIKE '_문자' : 비교할 대상컬럼 값 문자앞에 아무글자나 딱 한 글자가 있는 값을 조회
        비교할 대상 컬럼 LIKE '문자_' : 비교할 대상컬럼 값 문자뒤에 아무글자나 딱 한 글자가 있는 값을 조회
        비교할 대상 컬럼 LIKE '_문자_' : 비교할 대상컬럼 값 문자앞뒤에 아무글자나 딱 한 글자가 있는 값을 조회
        
        비교할 대상 컬럼 LIKE '_문자____' : 내가 원하는 형태로 _를 통해서 문자 수 조절 가능

*/


select EMP_NAME, SALARY from employee
where EMP_NAME like '전%'

select EMP_NAME, SALARY from employee
where EMP_NAME like '%연'

select EMP_NAME, SALARY from employee
where EMP_NAME like '%하%'



select EMP_NAME, SALARY from employee
where EMP_NAME like '전_'

select EMP_NAME, SALARY from employee
where EMP_NAME like '_연'

select EMP_NAME, SALARY from employee
where EMP_NAME like '_하_'


-- 사원들 중에서 전화번호가 3번째 자리가 1인 사원들의 사번 사원명 전화번호 조회
select EMP_ID, EMP_NAME, PHONE from employee
where PHONE like '__1%'

-- 이메일 중 _앞에 글자가 3글자인 사원들의 사번, 이름, 이메일 조회
select EMP_ID, EMP_NAME, EMAIL from employee
-- where EMAIL like '____%' -> 와일드카드로 인식되기 때문에 정상적으로 출력될 수 없다
-- 와일드카드를 직접 문자로 사용할 때는 일반문자로 구분을 해줘야한다
-- ESCAPE OPTION을 등록해서 나만의 탈출문자를 사용할 수 있음
where EMAIL like '___/_%' escape '/';


/*
    <IN>
    WHERE절에 비교대상 컬럼값이 내가 제시한 목록중에 일치하는지 값이 있는지 검사하는 문법

    [표현법]
    비교대상 컬럼 IN (값,값,값,값....)
    
*/

-- 부서코드가 D6이거나 D8이거나 D5인 부서원들의 이름, 부서코드, 급여 조회
select EMP_NAME, DEPT_CODE, SALARY from employee
-- where DEPT_CODE = 'D6' or DEPT_CODE = 'D8' or DEPT_CODE = 'D5';
where DEPT_CODE in ('D6','D8','D5')


/* 
    <연산자 우선순위>
    1. 산술연산자
    2. 연결연산자
    3. 비교연산자
    4. IS NULL/ LIKE/ IN
    5. BETWEEN A AND B
    6. NOT
    7. AND
    8. OR

*/


===============================================================================
-- 실습

-- 이름이 '연'으로 끝나는 사원들의 사원명, 입사일 조회
select EMP_NAME, HIRE_DATE from employee
where EMP_NAME like '%연';

-- 전화번호 처음 3자리가 010이 아닌 사원들의 사원명, 전화번호 조회
select EMP_NAME, PHONE from employee
where PHONE NOT like '010%'

-- 이름에 '하'가 포함되어있고 급여가 240만원 이상인 사원들의 사원명, 급여 조회
select EMP_NAME, SALARY from employee
where EMP_NAME like '%하%' and SALARY >= 2400000

-- 부서(DEPT) 테이블에서 해외 영업부서인 부서들의 부서코드, 부서명 조회
select DEPT_ID, DEPT_TITLE from department
where DEPT_TITLE like '해외영업__'

-- 사수(MANAGER)가 없고 부서배치도 받지 않은 사원들의 사원명, 사번, 부서코드 조회
select EMP_NAME, EMP_ID, DEPT_CODE from employee
where MANAGER_ID is NULL and DEPT_CODE is NULL

-- 연봉(급여 * 12)이 3천만원 이상이고 보너스를 받지 않는 사원들의 사번, 사원명, 급여, 보너스 조회
select EMP_ID, EMP_NAME,SALARY, BONUS from employee
where (SALARY * 12) >= 30000000 and BONUS is null

-- 입사일이 '95/01/01' 이상이고 부서배치를 받지 않는 사원들의 사번, 사원명, 입사일, 부서코드 조회
select EMP_ID, EMP_NAME, HIRE_DATE, DEPT_CODE from employee
where DEPT_CODE is NULL and HIRE_DATE >= '95/01/01'

-- 급여가 200만원 이상이고 500만원 이하인 사원 중에서 입사일이 '01/01/01' 이상이고 보너스를 받지 않는
-- 사원들의 사번, 사원명, 급여, 입사일, 보너스 조회
select EMP_ID, EMP_NAME, SALARY, HIRE_DATE, BONUS from employee
where HIRE_DATE >= '01/01/01' and BONUS is null and SALARY >= 2000000 and SALARY <=5000000

-- 보너스를 포함 연봉((SALARY + (SALARY * BONUS)) * 12)이 NULL이 아니고 이름에 '하'가 포함된 사원들의 
-- 사번, 사원명, 급여, 보너스포함 연봉 조회
select EMP_ID, EMP_NAME, SALARY, BONUS from employee
where ((SALARY + (SALARY * BONUS)) * 12) IS NOT NULL and EMP_NAME like '%하%'



/*
    <ORDER BY절>
    데이터를 정렬해서 조회하기 위한 구문
    SELECT문 가장 마지막 줄에 작성, 실행순서 또한 가장 마지막에 실행이 된다
    
    [표현법]
    SELECT 조회할 컬럼
    FROM 테이블
    WHERE 조건식
    ORDER BY 정렬기준이 될 컬럼 | 별칭 | 컬럼순번 [ASC|DESC] [NULLS FIRST | NULLS LAST]
    - ASC : 오름차순(작은 값으로 시작해서 점점 커지는 것)
    - DESC : 내림차순(큰 값으로 시작해서 값이 점점 줄어드는 것)
    
    - NULL은 기본적으로 가장 큰 값으로 분류해서 정렬

*/

select * from employee
-- order by BONUS;
-- order by bonus asc nulls first;
order by BONUS DESC, SALARY ASC;
-- 정렬기준에 컬럼값이 동일할 경우 그 다음차순을 위해서 여러개의 기준 컬럼을 제시할 수 있다.

-- 전 사원의 사원명, 연봉 조회(이 때 연봉을 기준으로 내림차순 정렬)
select EMP_NAME, SALARY *12 연봉 from EMPLOYEE
-- order by SALARY * 12 desc;
order by 2 desc;







