/*
 * Copyright (c) 2018 SnappyData, Inc. All rights reserved.
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

package io.snappydata.hydra.dataExtractorTool;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import hydra.FileUtil;
import hydra.Log;
import io.snappydata.hydra.cdcConnector.SnappyCDCPrms;
import io.snappydata.hydra.cluster.SnappyBB;
import io.snappydata.hydra.cluster.SnappyTest;
import io.snappydata.hydra.security.SnappySecurityPrms;
import org.apache.commons.io.FileUtils;
import util.TestException;

public class DataExtractorToolTest extends SnappyTest {

  private static Integer expectedExceptionCnt = 0;
  private static Integer unExpectedExceptionCnt = 0;
  public static DataExtractorToolTest dataExtractorToolTest;

  public static void HydraTask_executeQuery() {
    int expectedExcptCnt = SnappySecurityPrms.getExpectedExcptCnt();
    int unExpectedExcptCnt = SnappySecurityPrms.getUnExpectedExcptCnt();

    if (dataExtractorToolTest == null) {
      dataExtractorToolTest = new DataExtractorToolTest();
    }
    dataExtractorToolTest.executeQuery();
    dataExtractorToolTest.validate(expectedExcptCnt, unExpectedExcptCnt);
  }

  public void executeQuery() {
    Log.getLogWriter().info("SP: Inside runDMLOps ");
    String queryFile = SnappySecurityPrms.getDataLocation();
    Connection conn = null;
    ArrayList queryArray = getQueryArr(queryFile);
    try {
      conn = getLocatorConnection();
      for (int i = 0; i < queryArray.size(); i++) {
        try {
          String queryStr = (String) queryArray.get(i);
          conn.createStatement().execute(queryStr);
          Log.getLogWriter().info("Query executed successfully");
        } catch (SQLException se) {
          if (se.getMessage().contains("SELECT")) {
            unExpectedExceptionCnt = unExpectedExceptionCnt + 1;
            Log.getLogWriter().info("Caught unExpected exception" + se.getMessage() + "\n" + se.getCause());
          } else if (se.getMessage().contains("Insert") || se.getMessage().contains("Update") || se.getMessage().contains("Delete") || se.getMessage().contains("PutInto")
              || se.getMessage().contains("TRUNCATE") || se.getMessage().contains("DROP") || se.getMessage().contains("ALTER") || se.getMessage().contains("CREATE")) {
            expectedExceptionCnt = expectedExceptionCnt + 1;
            Log.getLogWriter().info("Caught expected exception " + se.getMessage());
          } else
            Log.getLogWriter().info("Caught Exception in runDMLOps method " + se.getMessage() + "\n" + se.getCause());
        }
      }
    }
    catch(SQLException ex){
      throw new io.snappydata.test.util.TestException("Task executeQuery failed with : \n" + ex.getMessage());
    }
      closeConnection(conn);
  }

  public void validate(Integer expectedCnt, Integer unExpectedCnt) {
    if (unExpectedExceptionCnt != unExpectedCnt)
      throw new TestException("The Result is WRONG :Expected unExpectedExceptionCnt = " + unExpectedCnt + " but got " +
          unExpectedExceptionCnt);
    else
      Log.getLogWriter().info("Successfully Got expected unExpectedExceptionCnt " + unExpectedExceptionCnt);

    if (expectedExceptionCnt != expectedCnt)
      throw new TestException("The Result is WRONG :Expected expectedExceptionCnt = " + expectedCnt + " but got " +
          expectedExceptionCnt);
    else
      Log.getLogWriter().info("Successfully Got expected expectedExceptionCnt " + expectedExceptionCnt);

    unExpectedExceptionCnt = 0;
    expectedExceptionCnt = 0;
  }

  public static void HydraTask_ExtractData() {
    if (dataExtractorToolTest == null) {
      dataExtractorToolTest = new DataExtractorToolTest();
    }
    dataExtractorToolTest.extractData();
  }

  public void extractData() {
    Connection conn;
    String dest = getCurrentDirPath();
    try {
      conn = getLocatorConnection();
      String query1 = "call sys.DUMP_DATA('" + dest + "/recover_data_parquet','parquet','all','true')";
      conn.createStatement().execute(query1);
      String query2 = "call sys.DUMP_DDLS('" + dest + "/recover_ddls/')";
      conn.createStatement().execute(query2);
    } catch (Exception ex) {
      throw new io.snappydata.test.util.TestException("Task HydraTask_ExtractData failed with : \n" + ex.getMessage());
    }
    closeConnection(conn);
  }


  public static void HydraTask_modifyConf() {
    if (dataExtractorToolTest == null) {
      dataExtractorToolTest = new DataExtractorToolTest();
    }
    dataExtractorToolTest.modifyConf();
  }

  public void modifyConf() {
    String snappyPath = SnappyCDCPrms.getSnappyFileLoc();
    String nodeConfigInfo = SnappyCDCPrms.getNodeInfoForHA();
    File orgName = new File(snappyPath + "/conf/servers");
    File bkName = new File(snappyPath + "/conf/servers_bk");
    String dest1 = getCurrentDirPath() + File.separator + "catResults1.log";
    File logFile1 = new File(dest1);
    String dest2 = getCurrentDirPath() + File.separator + "catResults2.log";
    File logFile2 = new File(dest2);
    String dest3 = getCurrentDirPath() + File.separator + "catResults3.log";
    File logFile3 = new File(dest3);

    boolean isStartClusterForCPDE = SnappyDataExtractorToolTestPrms.getIsStartClusterForCPDE();
    try {
      if (isStartClusterForCPDE) {
        File tempConfFile = null;
        String cmd1 = " cat "+ orgName;
        ProcessBuilder pb1 = new ProcessBuilder("/bin/bash", "-c", cmd1);
        snappyTest.executeProcess(pb1,logFile1);
        FileInputStream fis = new FileInputStream(orgName);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
        String str;

        tempConfFile = (File) SnappyBB.getBB().getSharedMap().get("SERVER_CONF");
        String cmd2 = " cat "+ tempConfFile;
        ProcessBuilder pb2 = new ProcessBuilder("/bin/bash", "-c", cmd2);
        snappyTest.executeProcess(pb2,logFile2);
        FileWriter fw1 = new FileWriter(tempConfFile, true);
        while ((str = br.readLine()) != null) {
          String strString = str + "\n";
          Log.getLogWriter().info("SP: The string to be written in conf file is " + strString);
          fw1.write(strString);
        }
        fw1.close();
        orgName.delete();
        orgName.createNewFile();
        FileUtils.copyFile(tempConfFile,orgName);
        String cmd3 = " cat "+ orgName;
        ProcessBuilder pb3 = new ProcessBuilder("/bin/bash", "-c", cmd3);
        snappyTest.executeProcess(pb3,logFile3);

        deleteFiles(bkName);
        deleteFiles(tempConfFile);

      } else {
        Vector hostList = SnappyCDCPrms.getNodeName();
        FileUtils.copyFile(orgName, bkName);
        orgName.delete();
        orgName.createNewFile();
        FileWriter fw = new FileWriter(orgName, true);
        Log.getLogWriter().info("SP: The hostList size = " + hostList.size());
        for (int i = 0; i < hostList.size(); i++) {
          String nodeName = String.valueOf(hostList.get(i));
          Log.getLogWriter().info("The nodeName is " + nodeName);
          String nodeInfo = nodeName + nodeConfigInfo ;//+ " -locators = " + endpoints.get(0);
          Log.getLogWriter().info("The nodeInfo is  " + nodeInfo);
          String nodeConfig = nodeInfo + "\n";
          fw.write(nodeConfig);
        }
        fw.close();
        SnappyBB.getBB().getSharedMap().put("SERVER_CONF", bkName);
       // deleteFiles(bkName);
      }
      Log.getLogWriter().info("Starting the cluster");
      startSnappyCluster();
      Log.getLogWriter().info("Finished cluster startUp.");
    } catch (FileNotFoundException e) {
      throw new io.snappydata.test.util.TestException("Caught FileNotFoundException in addNewNode method " + e.getMessage());
    } catch (IOException e) {
      throw new io.snappydata.test.util.TestException("Caught IOException in addNewNode method " + e.getMessage());
    } catch (Exception e) {
      throw new io.snappydata.test.util.TestException("Caught Exception in addNewNode method" + e.getMessage());
    }
  }

  public void deleteFiles(File fileName){
    try{
      //delete the temp conf file created.
      if (fileName.delete()) {
        Log.getLogWriter().info(fileName.getName() + " is deleted!");
      } else {
        Log.getLogWriter().info("Deleting the " +fileName+ " conf file operation failed.");
      }
    }
    catch(Exception ex) {

    }
  }

  public static void HydraTask_startClusterInRecoveryMode() {
    if (dataExtractorToolTest == null) {
      dataExtractorToolTest = new DataExtractorToolTest();
    }
    dataExtractorToolTest.startClusterInRecoveryMode();
  }

  public void startClusterInRecoveryMode() {
    Log.getLogWriter().info("SP: Inside startClusterInRecoveryMode ");
    try {
      String snappyPath = SnappyCDCPrms.getSnappyFileLoc();
      String dest = getCurrentDirPath() + File.separator + "RecoveryModeCluster.log";
      File logFile = new File(dest);
      String command = snappyPath + "/sbin/snappy-start-all.sh -r";
      ProcessBuilder pbClustStart = new ProcessBuilder("/bin/bash", "-c", command);
      Long startTime1 = System.currentTimeMillis();
      snappyTest.executeProcess(pbClustStart, logFile);
      Long totalTime1 = (System.currentTimeMillis() - startTime1);
      Log.getLogWriter().info("The cluster took " + totalTime1 + " ms to start in recovery mode");
    } catch (Exception ex) {
      throw new io.snappydata.test.util.TestException("Caught ioException in  startClusterIn Recovery Mode method " + ex.getMessage());
    }
  }

  public ArrayList getQueryArr(String fileName) {
    Log.getLogWriter().info("Inide getQueryArray");
    Log.getLogWriter().info("File Name = " + fileName);
    ArrayList<String> queries = new ArrayList<String>();
    try {
      BufferedReader br = new BufferedReader(new FileReader(fileName));
      String line = null;
      while ((line = br.readLine()) != null) {
        String[] splitData = line.split(";");
        for (int i = 0; i < splitData.length; i++) {
          if (!(splitData[i] == null) || !(splitData[i].length() == 0)) {
            queries.add(splitData[i]);
          }
        }
      }
      br.close();
    } catch (FileNotFoundException e) {
      Log.getLogWriter().info("Caught fileNotFound exception in getQueryArr method ");
    } catch (IOException io) {
      Log.getLogWriter().info("Caught ioException in getQueryArr method ");
    }
    return queries;
  }
}