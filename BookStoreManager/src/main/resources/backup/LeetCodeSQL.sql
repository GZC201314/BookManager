/**
  175. 组合两个表
 */

Create table Person
(
    PersonId  int,
    FirstName varchar(255),
    LastName  varchar(255)
);
Create table Address
(
    AddressId int,
    PersonId  int,
    City      varchar(255),
    State     varchar(255)
);
Truncate table Person;
insert into Person (PersonId, LastName, FirstName)
values ('1', 'Wang', 'Allen');
Truncate table Address;
insert into Address (AddressId, PersonId, City, State)
values ('1', '2', 'New York City', 'New York');


select firstname, lastname, city, state
from Person
         left join Address on Person.PersonID = Address.PersonID;


/**
  176. 第二高的薪水

  编写一个 SQL 查询，获取 Employee 表中第二高的薪水（Salary）。
 */
Create table If Not Exists Employee
(
    Id     int,
    Salary int
);
Truncate table Employee;
insert into Employee (Id, Salary)
values ('1', '100');
insert into Employee (Id, Salary)
values ('2', '200');
insert into Employee (Id, Salary)
values ('3', '300');
SELECT IFNULL(
               (SELECT DISTINCT Salary
                FROM Employee
                ORDER BY Salary DESC
                LIMIT 1 OFFSET 1),
               NULL) AS SecondHighestSalary;


/**
    177. 第N高的薪水
    编写一个 SQL 查询，获取 Employee 表中第 n 高的薪水（Salary）。
 */
CREATE FUNCTION getNthHighestSalary(N INT) RETURNS INT
BEGIN
    RETURN (
        # Write your MySQL query statement below.
        SELECT DISTINCT salary
        FROM (
                 SELECT salary,
                        @r :=
                                IF(@p = salary, @r, @r + 1) AS rnk,
                        @p := salary
                 FROM employee,
                      (SELECT @r := 0, @p := NULL) init
                 ORDER BY salary DESC
             ) tmp
        WHERE rnk = N
    );
END

