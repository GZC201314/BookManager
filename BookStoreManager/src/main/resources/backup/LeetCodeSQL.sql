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

/**
178. 分数排名

编写一个 SQL 查询来实现分数排名。

如果两个分数相同，则两个分数排名（Rank）相同。请注意，平分后的下一个名次应该是下一个连续的整数值。换句话说，名次之间不应该有“间隔”。

*/

Create table If Not Exists Scores (Id int, Score DECIMAL(3,2));
Truncate table Scores;
insert into Scores (Id, Score) values ('1', '3.5');
insert into Scores (Id, Score) values ('2', '3.65');
insert into Scores (Id, Score) values ('3', '4.0');
insert into Scores (Id, Score) values ('4', '3.85');
insert into Scores (Id, Score) values ('5', '4.0');
insert into Scores (Id, Score) values ('6', '3.65');

SELECT Score, dense_rank() OVER(order by Score desc) as `Rank` FROM scores ORDER BY Score DESC;


/**
180. 连续出现的数字

编写一个SQL查询，查找所有至少连续出现三次的数字。

返回的结果表中的数据可以按任意顺序排列。

*/

Create table If Not Exists Logs (Id int, Num int);
Truncate table Logs;
insert into Logs (Id, Num) values ('1', '1');
insert into Logs (Id, Num) values ('2', '1');
insert into Logs (Id, Num) values ('3', '1');
insert into Logs (Id, Num) values ('4', '2');
insert into Logs (Id, Num) values ('5', '1');
insert into Logs (Id, Num) values ('6', '2');
insert into Logs (Id, Num) values ('7', '2');

SELECT DISTINCT Num ConsecutiveNums
FROM(
SELECT *,
      ROW_NUMBER() OVER (PARTITION BY Num ORDER BY Id) rownum,
      ROW_NUMBER() OVER (ORDER BY Id) id2
FROM LOGS
) t
GROUP BY (id2-rownum),Num 
HAVING COUNT(*)>=3;

/**
181. 超过经理收入的员工

Employee 表包含所有员工，他们的经理也属于员工。每个员工都有一个 Id，此外还有一列对应员工的经理的 Id。
*/

Create table If Not Exists Employee (Id int, Name varchar(255), Salary int, ManagerId int);
Truncate table Employee;
insert into Employee (Id, Name, Salary, ManagerId) values ('1', 'Joe', '70000', '3');
insert into Employee (Id, Name, Salary, ManagerId) values ('2', 'Henry', '80000', '4');
insert into Employee (Id, Name, Salary, ManagerId) values ('3', 'Sam', '60000', 'None');
insert into Employee (Id, Name, Salary, ManagerId) values ('4', 'Max', '90000', 'None');

Select a.Name as Employee from Employee as a,Employee as b
where a.ManagerId=b.Id and a.Salary>b.Salary;