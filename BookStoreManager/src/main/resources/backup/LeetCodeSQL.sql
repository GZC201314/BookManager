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


/**

182. 查找重复的电子邮箱

编写一个 SQL 查询，查找 Person 表中所有重复的电子邮箱。
*/
Create table If Not Exists Person (Id int, Email varchar(255));
Truncate table Person;
insert into Person (Id, Email) values ('1', 'a@b.com');
insert into Person (Id, Email) values ('2', 'c@d.com');
insert into Person (Id, Email) values ('3', 'a@b.com');

SELECT Email FROM person GROUP BY Email HAVING COUNT(Email)>1;

/**

183. 从不订购的客户

某网站包含两个表，Customers 表和 Orders 表。编写一个 SQL 查询，找出所有从不订购任何东西的客户。
*/
Create table If Not Exists Customers (Id int, Name varchar(255));
Create table If Not Exists Orders (Id int, CustomerId int);
Truncate table Customers;
insert into Customers (Id, Name) values ('1', 'Joe');
insert into Customers (Id, Name) values ('2', 'Henry');
insert into Customers (Id, Name) values ('3', 'Sam');
insert into Customers (Id, Name) values ('4', 'Max');
Truncate table Orders;
insert into Orders (Id, CustomerId) values ('1', '3');
insert into Orders (Id, CustomerId) values ('2', '1');

SELECT c.Name AS Customers FROM Customers c LEFT JOIN Orders o  ON c.Id =o.CustomerId where o.Id IS NULL;

/**
184. 部门工资最高的员工

Employee 表包含所有员工信息，每个员工有其对应的 Id, salary 和 department Id。
*/
drop table Employee;
drop table Department;
Create table If Not Exists Employee (Id int, Name varchar(255), Salary int, DepartmentId int);
Create table If Not Exists Department (Id int, Name varchar(255));
Truncate table Employee;
insert into Employee (Id, Name, Salary, DepartmentId) values ('1', 'Joe', '70000', '1');
insert into Employee (Id, Name, Salary, DepartmentId) values ('2', 'Jim', '90000', '1');
insert into Employee (Id, Name, Salary, DepartmentId) values ('3', 'Henry', '80000', '2');
insert into Employee (Id, Name, Salary, DepartmentId) values ('4', 'Sam', '60000', '2');
insert into Employee (Id, Name, Salary, DepartmentId) values ('5', 'Max', '90000', '1');
Truncate table Department;
insert into Department (Id, Name) values ('1', 'IT');
insert into Department (Id, Name) values ('2', 'Sales');

SELECT
	Department. NAME AS 'Department',
	Employee. NAME AS 'Employee',
	Salary
FROM
	Employee
JOIN Department ON Employee.DepartmentId = Department.Id
WHERE
	(
		Employee.DepartmentId,
		Salary
	) IN (
		SELECT
			DepartmentId,
			MAX(Salary)
		FROM
			Employee
		GROUP BY
			DepartmentId
	);
/**
185. 部门工资前三高的所有员工

Employee 表包含所有员工信息，每个员工有其对应的工号 Id，姓名 Name，工资 Salary 和部门编号 DepartmentId 。
*/
Create table If Not Exists Employee (Id int, Name varchar(255), Salary int, DepartmentId int);
Create table If Not Exists Department (Id int, Name varchar(255));
Truncate table Employee;
insert into Employee (Id, Name, Salary, DepartmentId) values ('1', 'Joe', '85000', '1');
insert into Employee (Id, Name, Salary, DepartmentId) values ('2', 'Henry', '80000', '2');
insert into Employee (Id, Name, Salary, DepartmentId) values ('3', 'Sam', '60000', '2');
insert into Employee (Id, Name, Salary, DepartmentId) values ('4', 'Max', '90000', '1');
insert into Employee (Id, Name, Salary, DepartmentId) values ('5', 'Janet', '69000', '1');
insert into Employee (Id, Name, Salary, DepartmentId) values ('6', 'Randy', '85000', '1');
insert into Employee (Id, Name, Salary, DepartmentId) values ('7', 'Will', '70000', '1');
Truncate table Department;
insert into Department (Id, Name) values ('1', 'IT');
insert into Department (Id, Name) values ('2', 'Sales');

SELECT
	d.`Name` AS Department,
	e1.`Name` AS Employee,
	e1.Salary AS Salary
FROM
	employee e1
JOIN department d ON (e1.DepartmentId = d.Id)
WHERE
	3 > (
		SELECT
			COUNT(DISTINCT(e2.Salary))
		FROM
			employee e2
		WHERE
			e1.Salary < e2.Salary
		AND e1.DepartmentId = e2.DepartmentId
	);