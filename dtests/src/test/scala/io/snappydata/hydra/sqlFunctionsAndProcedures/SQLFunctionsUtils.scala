/*
 * Copyright (c) 2017-2019 TIBCO Software Inc. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License. See accompanying
 * LICENSE file.
 */
package io.snappydata.hydra.sqlFunctionsAndProcedures

object SQLFunctionsUtils {
  // scalastyle:off println

  val rowTbl : String = "row_table"
  val columnTbl : String = "column_table"
  val usingCol : String = " USING COLUMN"
  val usingRow : String = " USING ROW"
  val createTbl : String = "CREATE TABLE IF NOT EXISTS "
  val insertInto : String = "INSERT INTO "
  val values : String = " VALUES "
  val dropTbl : String = "DROP TABLE IF EXISTS "
  /**
    *  Below queries test the functions :
    *  1. date,
    *  2. date_add,
    *  3. date_sub,
    *  4. datediff,
    *  5. date_format
    */
  val dateSet1 : String = "(1, 'AAA', current_date, '2020-01-19', 5, 3)"
  val dateSet2 : String = "(2, 'BBB', current_date, current_date, 7, 6)"
  val dateSet3 : String = "(3, 'CCC', '2019-12-31', '2020-12-31', 10, 12)"
  val createColTypeTblInSpark : String = createTbl + columnTbl +
    "(id int, name String, date1 date, date2 date, n1 int, n2 int)"
  val createRowTypeTblInSpark : String = createTbl + rowTbl +
    "(id int, name String, date1 date, date2 date, n1 int, n2 int)"
  val createColumnTbl_DateFunctions_1 : String = createTbl + columnTbl +
    "(id int, name String, date1 date, date2 date, n1 int, n2 int)" + usingCol
  val createRowTbl_DateFunctions_1 : String = createTbl + rowTbl +
    "(id int, name String, date1 date, date2 date, n1 int, n2 int)" + usingRow
  val selectQueryOnColTbl_DateFunctions_1 : String =
    "SELECT id, name, date1, date_add(date1, n1), " +
    "date_sub(date2, n2), datediff(date1, date2), date_format(current_date, 'y') " +
    "FROM " + columnTbl + " ORDER BY id"
  val selectQueryOnRowTbl_DateFunctions_1 : String =
    "SELECT id, name, date1, date_add(date1, n1), " +
    "date_sub(date2, n2), datediff(date1, date2), date_format(current_date, 'y') " +
    "FROM " + rowTbl + " ORDER BY id"
  val selectMonth_In_DateFormatFunc : String = "SELECT date_format(current_date, 'M')"
  val selectDay_DateFormatFunc : String = "SELECT date_format(current_date, 'd')"
  val dropColumnTbl_DateFunctions_1 : String = dropTbl + columnTbl
  val dropRowTbl_DateFunctions_1 : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  6. repeat
    *  7. reverse
    *  (NOTE : reverse logic for arrays is available since  Spark 2.4.0,
    *  test to be added after spark 2.4.0 merge
    */
  val rseRptSet1 : String = "(1,'TIBCO ComputeDB','TIBCO ComputeDB-')"
  val rseRptSet2 : String = "(2,'SQL Functions','SQL Functions ')"
  val createColTypeTbl_RseRpt_Spark : String = createTbl + columnTbl +
  "(id int,reversename string,repeatname string)"
  val createRowTypeTbl_rserpt_Spark : String = createTbl + rowTbl +
    "(id int,reversename string,repeatname string)"
  val createColumnTbl_RseRpt : String = createTbl + columnTbl +
  "(id int,reversename string,repeatname string) " + usingCol
  val createRowTbl_RseRpt : String = createTbl + rowTbl +
    "(id int,reversename string,repeatname string) " + usingRow
  val select_ColTbl_RseRpt : String = "SELECT id, reverse(reversename), " +
    "repeat(repeatname, 3) FROM " + columnTbl +  " ORDER BY id"
  val select_RowTbl_RseRpt : String = "SELECT id, reverse(reversename), " +
    "repeat(repeatname, 3) FROM " + rowTbl +  " ORDER BY id"
  val dropColTbl_RseRpt : String = dropTbl  + columnTbl
  val dropRowTbl_RseRpt : String = dropTbl + rowTbl
  /**
    * Below queries test the functions :
    * 8. !(Logical Not)
    * (Logical NOT is not working in Snappy Cluster)
    * hence test case needs to be add.
    * Add the boolean column and data once issue resolved.
    * 9. & (Bitwise AND)
    * 10. ^ (Bitwise exclusiveOR/ExOR)
    * val NOT_AND_ExOR_Set1 : String="(1,7,3,true)"
    * val createColTypeTbl_NOT_AND_ExOR_Spark : String= createTbl + columnTbl +
    * "(id int,n1 int,n2 int,b boolean)"
    *  11. | (Bitwise OR)
    *  12. ~ (Bitwise NOT)
    */
  val NOT_AND_ExOR_Set1 : String = "(1,7,3,333)"
  val NOT_AND_ExOR_Set2 : String = "(2,3,5,7)"
  val NOT_AND_ExOR_Set3 : String = "(3,5,6,1098)"
  val NOT_AND_ExOR_Set4 : String = "(4,1,8,1234567)"
  val createColTypeTbl_NOT_AND_ExOR_Spark : String = createTbl + columnTbl +
  "(id int,n1 int,n2 int,n3 int)"
  val createRowTypeTbl_NOT_AND_ExOR_Spark : String = createTbl + rowTbl +
    "(id int,n1 int,n2 int,n3 int)"
  val createColumnTbl_NOT_AND_ExOR : String = createTbl + columnTbl +
    "(id int,n1 int,n2 int,n3 int) " + usingCol
  val createRowTbl_NOT_AND_ExOR : String = createTbl + rowTbl +
    "(id int,n1 int,n2 int,n3 int) " + usingRow
  val select_ColTbl_NOT_AND_ExOR : String = "SELECT id, (n1 & n2) as BitwiseAND, " +
    "(n1 ^ n2) as BitwiseExOR, (n1 | n2) as BitwiseOR,~(n3) as BitwiseNOT " +
    "FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_NOT_AND_ExOR : String = "SELECT id, (n1 & n2) as BitwiseAND, " +
    "(n1 ^ n2) as BitwiseExOR, (n1 | n2) as BitwiseOR,~n3 as BitwiseNOT " +
    "FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_NOT_AND_ExOR : String = dropTbl + columnTbl
  val dropRowTbl_NOT_AND_ExOR : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  13. day ,14. dayofmonth, 15. dayofyear, 16. last_day,
    *  17. month, 18. next_day, 19. weekofyear
    *   20 year
    */
  val day_Month_Year_Set1 : String = "(1,current_date)"
  val day_Month_Year_Set2 : String = "(2,'2014-04-05')"
  val createColTypeTbl_Day_Month_Year_Spark : String = createTbl + columnTbl + " (id int,dt date)"
  val createRowTypeTbl_Day_Month_Year_Spark : String = createTbl + rowTbl + " (id int,dt date)"
  val createColumnTbl_Day_Month_Year : String = createTbl + columnTbl +
    "(id int,dt date) " + usingCol
  val createRowTbl_Day_Month_Year : String = createTbl + rowTbl +
    "(id int,dt date) " + usingRow
  val select_ColTbl_Day_Month_Year : String = "SELECT id,day(dt),dayofmonth(dt)," +
    "dayofyear(dt),last_day(dt),month(dt),next_day(dt,'FR'),weekofyear(dt)," +
    "year(dt) FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_Day_Month_Year : String = "SELECT id,day(dt),dayofmonth(dt)," +
    "dayofyear(dt),last_day(dt),month(dt),next_day(dt,'FR'),weekofyear(dt)," +
    "year(dt) FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_Day_Month_Year : String = dropTbl  + columnTbl
  val dropRowTbl_Day_Month_Year : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  21. map , 22. map_keys, 23. map_values
    */
  val map_Keys_Values_Set1 : String = "1,MAP('Maths',14,'Science',18,'Hindi',15)"
  val map_Keys_Values_Set2 : String = "2,MAP('Maths',19,'Science',19,'Hindi',19)"
  val createColTypeTbl_map_Keys_Values_Spark : String = createTbl + columnTbl +
    " (id int,marks map<string,int>)"
  val createColumnTbl_map_Keys_Values : String = createTbl + columnTbl +
    "(id int,marks map<string,int>) " + usingCol
  val select_ColTbl_map_Keys_Values : String = "SELECT id,map_keys(marks)," +
    "map_values(marks),marks FROM " + columnTbl + " ORDER BY ID"
  val dropColTbl_map_Keys_Values : String = dropTbl  + columnTbl
  /**
    *  Below queries test the functions :
    *  24. array , 25. array_contains
    */
  val array_Contains_Set1 : String = "1,Array(3,5,6,8,1)"
  val array_Contains_Set2 : String = "2,Array(13,45,66,98,101)"
  val createColTypeTbl_array_Contains_Spark : String = createTbl + columnTbl +
    " (id int,arr Array<Int>)"
  val createColumnTbl_array_Contains_Values : String = createTbl + columnTbl +
    "(id int,arr Array<Int>) " + usingCol
  val select_ColTbl_array_Contains : String = "SELECT id,arr as Array,array_contains(arr,8) " +
    "FROM " + columnTbl + " ORDER BY ID"
  val dropColTbl_array_Contains : String = dropTbl  + columnTbl
  /**
    *  Below queries test the functions :
    *  26. and, 27. or , 28. not
    */
  val And_Or_Not_Set1 : String = "(1,false,false,false)"
  val And_Or_Not_Set2 : String = "(2,false,true,false)"
  val And_Or_Not_Set3 : String = "(3,true,false,true)"
  val And_Or_Not_Set4 : String = "(4,true,true,true)"
  val createColTypeTbl_And_Or_Not_Spark : String = createTbl + columnTbl +
    "(id int,b1 boolean,b2 boolean,b boolean)"
  val createRowTypeTbl_And_Or_Not_Spark : String = createTbl + rowTbl +
    "(id int,b1 boolean,b2 boolean,b boolean)"
  val createColumnTbl_And_Or_Not : String = createTbl + columnTbl +
    "(id int,b1 boolean,b2 boolean,b boolean) " + usingCol
  val createRowTbl_And_Or_Not : String = createTbl + rowTbl +
    "(id int,b1 boolean,b2 boolean,b boolean) " + usingRow
  val select_ColTbl_And_Or_Not : String = "SELECT id,(b1 AND b2) as LogicalAND," +
    "(b1 OR b2) as LogicalOR, NOT(b) as LogicalNOT FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_And_Or_Not : String = "SELECT id,(b1 AND b2) as LogicalAND," +
    "(b1 OR b2) as LogicalOR, NOT(b) as LogicalNOT FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_And_Or_Not : String = dropTbl  + columnTbl
  val dropRowTbl_And_Or_Not : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  30. size.
    *  NOTE : Following test case is Pending.
    *  SET spark.sql.legacy.sizeOfNull is set to false, the function returns null for null input
    */
  val size_Set1 : String = "1,Array(10,20,30,40,50,60),Map('Rahul',40.45,'Virat',54.32,'Dhoni',56.78)"
  val size_Set2 : String = "2,Array(80,99,65,78),null"
  val size_Set3 : String = "3,null,Map('AAA',0.0,'BBB',6.67)"
  val size_Set4 : String = "4,null,null"
  val createColTypeTbl_Size_Spark : String = createTbl + columnTbl +
    " (id int,testArr Array<Int>,testMap Map<String,Double>)"
  val createColumnTbl_Size : String = createTbl + columnTbl +
    "(id int,testArr Array<Int>,testMap Map<String,Double>) " + usingCol
  val select_ColTbl_Size : String = "SELECT id,size(testArr),size(testMap) FROM " +
    columnTbl + " ORDER BY ID"
  val dropColTbl_Size : String = dropTbl  + columnTbl
  /**
    *  Below queries test the functions :
    *  31. rpad, 32. in
    */
  val rpad_in_Set1 : String = "(1,'TIBCO ComputeDB')"
  val rpad_in_Set2 : String = "(2,'Spot fire')"
  val rpad_in_Set3 : String = "(3,'DBVisualizer')"
  val createColTypeTbl_rpad_in_Spark : String = createTbl + columnTbl +
    "(id int,testStr string)"
  val createRowTypeTbl_rpad_in_Spark : String = createTbl + rowTbl +
    "(id int,testStr string)"
  val createColumnTbl_rpad_in : String = createTbl + columnTbl +
    "(id int,testStr string) " + usingCol
  val createRowTbl_rpad_in : String = createTbl + rowTbl +
    "(id int,testStr string) " + usingRow
  val select_ColTbl_rpad_in : String = "SELECT id,rpad(testStr,50,'-The TIBCO Product')," +
    "'Spot fire' in(testStr) FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_rpad_in : String = "SELECT id,rpad(testStr,50,'-The TIBCO Product')," +
    "'Spot fire' in(testStr) FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_rpad_in : String = dropTbl  + columnTbl
  val dropRowTbl_rpad_in : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  33. hour, 34. minute, 35. second
    */
  val hr_min_sec_Set1 : String = "(1,'2019-03-29 12:58:59')"
  val hr_min_sec_Set2 : String = "(2,'2020-11-14 23:45:12')"
  val createColTypeTbl_hr_min_sec_Spark : String = createTbl + columnTbl + "(id int,ts timestamp)"
  val createRowTypeTbl_hr_min_sec_Spark : String = createTbl + rowTbl + "(id int,ts timestamp)"
  val createColumnTbl_hr_min_sec : String = createTbl + columnTbl +
    "(id int,ts timestamp) " + usingCol
  val createRowTbl_hr_min_sec : String = createTbl + rowTbl +
    "(id int,ts timestamp) " + usingRow
  val select_ColTbl_hr_min_sec : String = "SELECT id,hour(ts),minute(ts),second(ts) FROM " +
    columnTbl + " ORDER BY id"
  val select_RowTbl_hr_min_sec : String = "SELECT id,hour(ts),minute(ts),second(ts) FROM " +
    rowTbl + " ORDER BY id"
  val dropColTbl_hr_min_sec : String = dropTbl  + columnTbl
  val dropRowTbl_hr_min_sec : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  36. ascii, 37. months_between, 38. current_timestamp
    */
  val ascii_mnthbet_ts_Set1 : String = "(1,'Spark','S','2019-09-07',current_timestamp)"
  val ascii_mnthbet_ts_Set2 : String = "(2,'abcd','a','2018-11-15','2017-12-25 16:55:43')"
  val ascii_mnthbet_ts_Set3 : String = "(3,'0123','0','2019-12-31','2019-12-31 11:12:13')"
  val createColTypeTbl_ascii_mnthbet_ts_Spark : String = createTbl + columnTbl +
    "(id int,s1 string,s2 string,dt1 date,dt2 timestamp)"
  val createRowTypeTbl_ascii_mnthbet_ts_Spark : String = createTbl + rowTbl +
    "(id int,s1 string,s2 string,dt1 date,dt2 timestamp)"
  val createColumnTbl_ascii_mnthbet_ts : String = createTbl + columnTbl +
    "(id int,s1 string,s2 string,dt1 date,dt2 timestamp) " + usingCol
  val createRowTbl_ascii_mnthbet_ts : String = createTbl + rowTbl +
    "(id int,s1 string,s2 string,dt1 date,dt2 timestamp) " + usingRow
  val select_ColTbl_ascii_mnthbet_ts : String = "SELECT id,ascii(s1),ascii(s2)," +
    "months_between(dt1,dt2) FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_ascii_mnthbet_ts : String = "SELECT id,ascii(s1),ascii(s2)," +
    "months_between(dt1,dt2) FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_ascii_mnthbet_ts : String = dropTbl  + columnTbl
  val dropRowTbl_ascii_mnthbet_ts : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  39. string, 40. substr, 41. substring
    */
  val str_subStr = new Array[String](2)
  str_subStr(0) = "(1,123456,43.45,'TIBCO ComputeDB')"
  str_subStr(1) = "(2,9999,1004.56,'JDBC Driver')"
  val createColTypeTbl_str_substr_Spark : String = createTbl + columnTbl +
    "(id int,testNumber int,testDouble Double,testStr string)"
  val createRowTypeTbl_str_substr_Spark : String = createTbl + rowTbl +
    "(id int,testNumber int,testDouble Double,testStr string)"
  val createColumnTbl_str_substr : String = createTbl + columnTbl +
    "(id int,testNumber int,testDouble Double,testStr string) " + usingCol
  val createRowTbl_str_substr : String = createTbl + rowTbl +
    "(id int,testNumber int,testDouble Double,testStr string) " + usingRow
  val select_ColTbl_str_substr : String = "SELECT id,string(testNumber),string(testDouble)," +
    "substr(teststr,0,5),substring(teststr,0,5),substr(teststr,3),substring(teststr,3)," +
    "substr(teststr,-5),substring(teststr,-5) FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_str_substr : String = "SELECT id,string(testNumber),string(testDouble)," +
    "substr(teststr,0,5),substring(teststr,0,5),substr(teststr,3),substring(teststr,3)," +
    "substr(teststr,-5),substring(teststr,-5) FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_str_substr : String = dropTbl  + columnTbl
  val dropRowTbl_str_substr : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  42. >, 43. >=, 44. <, 45. <=, 46 hypot (hypot already done)
    *  So 46 is duplicate number and need not to be removed.
    *  Need to add the test case for complex data types for 42 to 45.
    */
  val hypot_gt_lt = new Array[String](9)
  hypot_gt_lt(0) = "(1,15,12)"
  hypot_gt_lt(1) = "(2,4,5)"
  hypot_gt_lt(2) = "(3,100,20)"
  hypot_gt_lt(3) = "(4,10,68)"
  hypot_gt_lt(4) = "(5,NULL,NULL)"
  hypot_gt_lt(5) = "(6,8,NULL)"
  hypot_gt_lt(6) = "(7,NULL,225)"
  hypot_gt_lt(7) = "(8,4,4)"
  val createColTypeTbl_hypot_gt_lt_Spark : String = createTbl + columnTbl +
    "(id int,n1 int,n2 int)"
  val createRowTypeTbl_hypot_gt_lt_Spark : String = createTbl + rowTbl +
    "(id int,n1 int,n2 int)"
  val createColumnTbl_hypot_gt_lt : String = createTbl + columnTbl +
    "(id int,n1 int,n2 int) " + usingCol
  val createRowTbl_hypot_gt_lt : String = createTbl + rowTbl +
    "(id int,n1 int,n2 int) " + usingRow
  val select_ColTbl_hypot_gt_lt : String = "SELECT id,(n1>n2) as GT,(n1>=n2) as GTEQ," +
    "(n1<n2) as LT,(n1<=n2) as LTEQ,hypot(n1,n2) as HYPOT FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_hypot_gt_lt : String = "SELECT id,(n1>n2) as GT,(n1>=n2) as GTEQ," +
    "(n1<n2) as LT,(n1<=n2) as LTEQ,hypot(n1,n2) as HYPOT FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_hypot_gt_lt : String = dropTbl  + columnTbl
  val dropRowTbl_hypot_gt_lt : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  46. space, 47. soundex
    */
  val spc_soundex = new Array[String](4)
  spc_soundex(0) = "(1,'TIBCO','TIBCO')"
  spc_soundex(1) = "(2,'Computation','Computation')"
  spc_soundex(2) = "(3,'Spark','Spark')"
  spc_soundex(3) = "(4,NULL,NULL)"
  val createColTypeTbl_spc_soundex_Spark : String = createTbl + columnTbl +
    "(id int,str1 string,str2 string)"
  val createRowTypeTbl_spc_soundex_Spark : String = createTbl + rowTbl +
    "(id int,str1 string,str2 string)"
  val createColumnTbl_spc_soundex : String = createTbl + columnTbl +
    "(id int,str1 string,str2 string) " + usingCol
  val createRowTbl_spc_soundex : String = createTbl + rowTbl +
    "(id int,str1 string,str2 string) " + usingRow
  val select_ColTbl_spc_soundex : String = "SELECT id,concat(space(20),str1)," +
    "soundex(str2) as soundex FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_spc_soundex : String = "SELECT id,concat(space(20),str1)," +
    "soundex(str2) as soundex FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_spc_soundex : String = dropTbl  + columnTbl
  val dropRowTbl_spc_soundex : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  48. xpath, 49. xpath_boolean, 50. xpath_double, 51. xpath_float,
    *  52. xpath_int, 53. xpath_long, 54. xpath_number, 55. xpath_short,
    *  56. xpath_string.
    */
  val xml : String = "<bookstore>" +
    "<book category=\"cooking\"><title lang=\"en\">Everyday Italian</title>" +
      "<author>Giada De Laurentiis</author><year>2005</year><price>30.00</price>" +
      "</book><book category=\"children\"><title lang=\"en\">Harry Potter</title>" +
      "<author>J K. Rowling</author><year>2005</year><price>29.99</price>" +
      "</book><book category=\"web\"><title lang=\"en\">XQuery Kick Start</title>" +
      "<author>James McGovern</author><author>Per Bothner</author><author>Kurt Cagle</author>" +
      "<author>James Linn</author><author>Vaidyanathan Nagarajan</author><year>2003</year>" +
      "<price>49.99</price></book><book category=\"web\"><title lang=\"en\">Learning XML</title>" +
      "<author>Erik T. Ray</author><year>2003</year><price>39.95</price></book></bookstore>"
  val xPath : String = "SELECT xpath(" + "'" + xml + "'," + "'/bookstore/book/author/text()')"
  val xPath_Boolean_true : String = "SELECT xpath_boolean(" + "'" + xml + "'," +
    "'/bookstore/book/price')"
  val xPath_Boolean_false : String = "SELECT xpath_boolean(" + "'" + xml + "'," +
    "'/bookstore/book/publisher')"
  val xPath_double : String = "SELECT xpath_double(" + "'" + xml + "'," +
    "'sum(/bookstore/book/price)')"
  val xPath_float : String = "SELECT xpath_double(" + "'" + xml + "'," +
    "'sum(/bookstore/book/oldprice)')"
  val xPath_int : String = "SELECT xpath_int(" + "'" + xml + "'," +
    "'sum(/bookstore/book/year)')"
  val xPath_long : String = "SELECT xpath_long(" + "'" + xml + "'," +
    "'sum(/bookstore/book/year)')"
  val xPath_number : String = "SELECT xpath_number(" + "'" + xml + "'," +
    "'sum(/bookstore/book/price)')"
  val xPath_short : String = "SELECT xpath_short(" + "'" + xml + "'," +
    "'sum(/bookstore/book/newprice)')"
  val xPath_string : String = "SELECT xpath_string(" + "'" + xml + "'," +
    "'/bookstore/book/title')"
  /**
    *  Below queries test the functions :
    *  57. trim, 58. ltrim, 59. rtrim, 60. isnotnull
    */
  val trim_isnotnull = new Array[String](2)
  trim_isnotnull(0) = "(1,' TIBCO-ComputeDB ','       TIBCO-ComputeDB'," +
    "'TIBCO-ComputeDB       ','JDBC Client')"
  trim_isnotnull(1) = "(2,' DB Visualizer    ','     DB Visualizer','DB Visualizer         ',null)"
  val createColTypeTbl_trim_isnotnull_Spark : String = createTbl + columnTbl +
    "(id int,s1 string,s2 string,s3 string,s4 string)"
  val createRowTypeTbl_trim_isnotnull_Spark : String = createTbl + rowTbl +
    "(id int,s1 string,s2 string,s3 string,s4 string)"
  val createColumnTbl_trim_isnotnull : String = createTbl + columnTbl +
    "(id int,s1 string,s2 string,s3 string,s4 string) " + usingCol
  val createRowTbl_trim_isnotnull : String = createTbl + rowTbl +
    "(id int,s1 string,s2 string,s3 string,s4 string) " + usingRow
  val select_ColTbl_trim_isnotnull : String = "SELECT id,trim(s1),ltrim(s2),rtrim(s3)," +
    "isnotnull(s4) FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_trim_isnotnull : String = "SELECT id,trim(s1),ltrim(s2),rtrim(s3)," +
    "isnotnull(s4) FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_trim_isnotnull : String = dropTbl  + columnTbl
  val dropRowTbl_trim_isnotnull : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  61. =, 62. ==, 63. <=>
    */
  val operators = new Array[String](6)
  operators(0) = "(1,100,250,'100','abc')"
  operators(1) = "(2,10,23,'10','xyz')"
  operators(2) = "(3,5,9,'5','9')"
  operators(3) = "(4,44,56,null,null)"
  operators(4) = "(5,34,78,'76','98')"
  val createColTypeTbl_operators_Spark : String = createTbl + columnTbl +
    "(id int,n1 int,n2 int,s1 string,s2 string)"
  val createRowTypeTbl_operators_Spark : String = createTbl + rowTbl +
    "(id int,n1 int,n2 int,s1 string,s2 string)"
  val createColumnTbl_operators : String = createTbl + columnTbl +
    "(id int,n1 int,n2 int,s1 string,s2 string) " + usingCol
  val createRowTbl_operators : String = createTbl + rowTbl +
    "(id int,n1 int,n2 int,s1 string,s2 string) " + usingRow
  val select_ColTbl_operators : String = "SELECT id,n1=s1,n2=s2,n1==s1,n2==s2," +
    "n1<=>s1,n2<=>s2 FROM " + columnTbl + " ORDER BY id"
  val select_RowTbl_operators : String = "SELECT id,n1=s1,n2=s2,n1==s1,n2==s2," +
    "n1<=>s1,n2<=>s2 FROM " + rowTbl + " ORDER BY id"
  val dropColTbl_operators : String = dropTbl  + columnTbl
  val dropRowTbl_operators : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  64. row_number(), 65. rank(), 66. dense_rank()
    */
  val rownumber_rank = new Array[String](13)
  rownumber_rank(0) = "('Suresh','First',98.6)"
  rownumber_rank(1) = "('Suresh','First',98.4)"
  rownumber_rank(2) = "('Mahesh','First',99.4)"
  rownumber_rank(3) = "('Giri','Second',96.7)"
  rownumber_rank(4) = "('Neel','Third',80.8)"
  rownumber_rank(5) = "('Suresh','First',98.6)"
  rownumber_rank(6) = "('Viru','Second',91.3)"
  rownumber_rank(7) = "('Mahi','Second',99.9)"
  rownumber_rank(8) = "('Sachin','Third',97.4)"
  rownumber_rank(9) = "('Anu','Second',76.7)"
  rownumber_rank(10) = "('Harish','Third',67.4)"
  rownumber_rank(11) = "('Kapil','Third',88.9)"
  rownumber_rank(12) = "('Rahul','Fifth',60.3)"
  val createColTypeTbl_rownumber_rank_Spark : String = createTbl + columnTbl +
    "(name string,class string,total double)"
  val createRowTypeTbl_rownumber_rank_Spark : String = createTbl + rowTbl +
    "(name string,class string,total double)"
  val createColumnTbl_rownumber_rank : String = createTbl + columnTbl +
    "(name string,class string,total double) " + usingCol
  val createRowTbl_rownumber_rank : String = createTbl + rowTbl +
    "(name string,class string,total double) " + usingRow
  val select_ColTbl_rownumber_rank : String = "SELECT *," +
    "row_number() over (partition by class order by total) row_number," +
    "rank() over (partition by class order by total) rank," +
    " dense_rank() over (partition by class order by total) dense_rank " +
    "FROM " + columnTbl
  val select_RowTbl_rownumber_rank : String = "SELECT *," +
    "row_number() over (partition by class order by total) row_number," +
    "rank() over (partition by class order by total) rank," +
    " dense_rank() over (partition by class order by total) dense_rank " +
    "FROM " + rowTbl
  val dropColTbl_rownumber_rank : String = dropTbl  + columnTbl
  val dropRowTbl_rownumber_rank : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  67. encode, 68. decode
    */
  val encode_decode = new Array[String](4)
  encode_decode(0) = "(1,'TIBCO')"
  encode_decode(1) = "(2,'ComputeDB')"
  encode_decode(2) = "(3,'Spark')"
  encode_decode(3) = "(4,'Docker')"
  val createColTypeTbl_encode_decode_Spark : String = createTbl + columnTbl +
    "(id int,testStr String)"
  val createRowTypeTbl_encode_decode_Spark : String = createTbl + rowTbl +
    "(id int,testStr String)"
  val createColumnTbl_encode_decode : String = createTbl + columnTbl +
    "(id int,testStr String) " + usingCol
  val createRowTbl_encode_decode : String = createTbl + rowTbl +
    "(id int,testStr String) " + usingRow
  val select_ColTbl_encode_decode : String = "SELECT id,decode(encode(testStr,'utf-16'),'utf-8')," +
    "decode(encode(testStr,'utf-8'),'us-ascii')," +
    "decode(encode(testStr,'us-ascii'),'utf-16') FROM " + columnTbl
  val select_RowTbl_encode_decode : String = "SELECT id,decode(encode(testStr,'utf-16'),'utf-8')," +
    "decode(encode(testStr,'utf-8'),'us-ascii')," +
    "decode(encode(testStr,'us-ascii'),'utf-16') FROM " + rowTbl
  val dropColTbl_encode_decode : String = dropTbl  + columnTbl
  val dropRowTbl_encode_decode : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  69. bigint, 70. binary, 71. boolean, 72. decimal,
    *  73. double, 74. float, 75. int, 76. smallint,
    *  77. tinyint
    */
  val dataTypes = new Array[String](1)
  dataTypes(0) = "(1,'45',56.7,121)"
  val createColTypeTbl_dataTypes_Spark : String = createTbl + columnTbl +
    "(id int,testStr string,testDouble double,testInt int)"
  val createRowTypeTbl_dataTypes_Spark : String = createTbl + rowTbl +
    "(id int,testStr string,testDouble double,testInt int)"
  val createColumnTbl_dataTypes : String = createTbl + columnTbl +
    "(id int,testStr string,testDouble double,testInt int) " + usingCol
  val createRowTbl_dataTypes : String = createTbl + rowTbl +
    "(id int,testStr string,testDouble double,testInt int) " + usingRow
  val select_ColTbl_dataTypes : String = "SELECT id,bigint(testStr) as bigint," +
    "binary(testStr) as binary,boolean(testStr) as boolean,decimal(testStr) as decimal," +
    "double(testStr) as double,float(testStr) as float,int(testStr) as int," +
    "smallint(testStr) as smallint,tinyint(testStr) as tinyint,decimal(testDouble) as decimal," +
    "float(testDouble) as float,boolean(0),boolean(1) FROM " + columnTbl
  val select_RowTbl_dataTypes : String = "SELECT id,bigint(testStr) as bigint," +
    "binary(testStr) as binary,boolean(testStr) as boolean,decimal(testStr) as decimal," +
    "double(testStr) as double,float(testStr) as float,int(testStr) as int," +
    "smallint(testStr) as smallint,tinyint(testStr) as tinyint,decimal(testDouble) as decimal," +
    "float(testDouble) as float,boolean(0),boolean(1) FROM " + rowTbl
  val dropColTbl_dataTypes : String = dropTbl  + columnTbl
  val dropRowTbl_dataTypes : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  78. hash, 79. sha, 80. sha1, 81. sha2.
    *   In this row table query has result mismatch, need to look.
    */
  val hash_sha = new Array[String](3)
  hash_sha(0) = "(1,'ComputeDB')"
  hash_sha(1) = "(2,'SnappyData')"
  hash_sha(2) = "(3,'TDV')"
  val createColTypeTbl_hash_sha_Spark : String = createTbl + columnTbl +
    "(id int,testStr string)"
  val createRowTypeTbl_hash_sha_Spark : String = createTbl + rowTbl +
    "(id int,testStr string)"
  val createColumnTbl_hash_sha : String = createTbl + columnTbl +
    "(id int,testStr string) " + usingCol
  val createRowTbl_hash_sha : String = createTbl + rowTbl +
    "(id int,testStr string) " + usingRow
  val select_ColTbl_hash_sha : String = "SELECT id,hash(1,testStr,Map('ABC','XYZ'))," +
    "sha(testStr),sha1(testStr),sha2(testStr,224),sha2(testStr,256)," +
    "sha2(testStr,384),sha2(testStr,512) FROM " + columnTbl
  val select_RowTbl_hash_sha : String = "SELECT id,hash(1,testStr)," +
    "sha(testStr),sha1(testStr),sha2(testStr,224),sha2(testStr,256)," +
    "sha2(testStr,384),sha2(testStr,512) FROM " + rowTbl
  val dropColTbl_hash_sha : String = dropTbl  + columnTbl
  val dropRowTbl_hash_sha : String = dropTbl + rowTbl
  /**
    *  Below queries test the functions :
    *  82. translate, 83. substring_index,
    *  84. split, 85. sentences.
    *   In this row table query has result mismatch, need to look.
    */
  val translate_split = new Array[String](3)
  translate_split(0) = "(1,'CDB','TIBCO-ComputeDB-The InmemoryDatabase'," +
    "'Spark SQL','TIBCO ComputeDB is a in-memory database and efficient compute engine')"
  translate_split(1) = "(2,null,null,null,null)"
  translate_split(2) = "(3,'','','','')"
  val createColTypeTbl_translate_split_Spark : String = createTbl + columnTbl +
    "(id int,str1 string,str2 string,str3 string,str4 string)"
  val createRowTypeTbl_translate_split_Spark : String = createTbl + rowTbl +
    "(id int,str1 string,str2 string,str3 string,str4 string)"
  val createColumnTbl_translate_split : String = createTbl + columnTbl +
    "(id int,str1 string,str2 string,str3 string,str4 string) " + usingCol
  val createRowTbl_translate_split : String = createTbl + rowTbl +
    "(id int,str1 string,str2 string,str3 string,str4 string) " + usingRow
  val select_ColTbl_translate_split : String = "SELECT id,translate(str1,'CB','TV')," +
    "substring_index(str2,'-',2),substring_index(str2,'-',1)," +
    "substring_index(str2,'-',-1),substring_index(str2,'-',-2)," +
    "substring_index(str2,'-',-3),split(str3,'S+'),sentences(str4) FROM " + columnTbl
  val select_RowTbl_translate_split : String = "SELECT id,translate(str1,'CB','TV')," +
    "substring_index(str2,'-',2),substring_index(str2,'-',1),substring_index(str2,'-',-1)," +
    "substring_index(str2,'-',-2),substring_index(str2,'-',-3)," +
    "split(str3,'S+'),sentences(str4) FROM " + rowTbl
  val dropColTbl_translate_split : String = dropTbl  + columnTbl
  val dropRowTbl_translate_split : String = dropTbl + rowTbl
}
