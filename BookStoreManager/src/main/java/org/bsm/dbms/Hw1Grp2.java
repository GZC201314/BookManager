//package org.bsm.dbms;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.apache.hadoop.conf.Configuration;
//import org.apache.hadoop.fs.FSDataInputStream;
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.hbase.HBaseConfiguration;
//import org.apache.hadoop.hbase.HColumnDescriptor;
//import org.apache.hadoop.hbase.HTableDescriptor;
//import org.apache.hadoop.hbase.TableName;
//import org.apache.hadoop.hbase.client.HBaseAdmin;
//import org.apache.hadoop.hbase.client.HTable;
//import org.apache.hadoop.hbase.client.Put;
//import org.apache.log4j.Level;
//import org.apache.log4j.Logger;
//
///**
// * complie Hw1Grp0.java: javac Hw1Grp0.java run code like: java Hw1Grp0
// * R=/hw1/supplier.tbl S=/hw1/nation.tbl join:R2=S3 res:R4,S5,R6
// */
//public class Hw1Grp2 {
//    private static final String TABLE_NAME = "Result";
//    private static final String COLUME_FAMILY = "res";
//    private static final String HDFS_BASE_PATH = "hdfs://192.168.15.137:9000";
//    private static String hdfsFile;
//    private static int groupBy;
//    private static String[] res;
//    private static Integer[] indexs;
//    private static Integer[] selectListResR;
//    private static Integer[] selectListResS;
//
//    private static List<String[]> result = new ArrayList<>();
//    private static Map<String, double[]> mapResult = new HashMap<>();
//
//    public static BufferedReader HDFSReader(String fileName) throws IOException, URISyntaxException {
//        // load HDFS file into inputstream
//        Configuration conf = new Configuration();
//        FileSystem fs = FileSystem.get(URI.create(fileName), conf);
//        Path path = new Path(fileName);
//        FSDataInputStream in_stream = fs.open(path);
//        BufferedReader in = new BufferedReader(new InputStreamReader(in_stream));
//        // in.close();
//        // fs.close();
//        return in;
//    }
//
//    public void groupBy(String filePath, int groupby, String[] listResS) throws IOException, URISyntaxException {
//        String splitFlag = "\\|";// \\|
//        // read file
//        BufferedReader instream = HDFSReader(filePath);
//        indexs = new Integer[listResS.length];
//        for (int i = 0; i < listResS.length; i++) {
//            if ("count".equals(listResS[i])) {
//                indexs[i] = groupby;
//            } else {
//                indexs[i] = Integer
//                        .parseInt(listResS[i].substring(listResS[i].indexOf("R") + 1, listResS[i].indexOf(")")));
//            }
//
//        }
//        String r;
//        Map<String, List<String[]>> hashTable = new HashMap<String, List<String[]>>();
//        while ((r = instream.readLine()) != null) {
//            List<String[]> listHashValues = new ArrayList<String[]>();
//            String[] currentLine = r.split(splitFlag);
//            String groupByKey = currentLine[groupby];
//            String[] valuesOfRes = new String[indexs.length];
//            for (int i = 0; i < indexs.length; i++) {
//                valuesOfRes[i] = currentLine[indexs[i].intValue()];
//            }
//            listHashValues.add(valuesOfRes);
//            if (hashTable.containsKey(groupByKey)) {
//                hashTable.get(groupByKey).add(valuesOfRes);
//            } else {
//                hashTable.put(groupByKey, listHashValues);
//            }
//        }
//
//        for (String key : hashTable.keySet()) {
//            List<String[]> ans = hashTable.get(key);
//            double[] values = new double[listResS.length];
//            for (int i = 0; i < listResS.length; i++) {
//                if ("count".equals(listResS[i])) {
//                    values[i] = ans.size();
//                } else if (listResS[i].indexOf("avg") != -1) {
//                    double sum = 0;
//                    for (int j = 0; j < ans.size(); j++) {
//                        sum += Double.parseDouble(ans.get(j)[i]);
//                    }
//                    values[i] = sum / ans.size();
//                } else if (listResS[i].indexOf("max") != -1) {
//                    double max = Double.parseDouble(ans.get(0)[i]);
//                    for (int j = 0; j < ans.size(); j++) {
//                        if (max < Double.parseDouble(ans.get(j)[i])) {
//                            max = Double.parseDouble(ans.get(j)[i]);
//                        }
//                    }
//                    values[i] = max;
//                }
//            }
//            mapResult.put(key, values);
//        }
//        System.out.println("this is test=======================================");
//    }
//
//    public void writeHBase(String tableName) throws IOException, URISyntaxException {
//        // get list of column key name
//        List<String> listOfColumnKey = new ArrayList<String>();
//        // create HBase table and delete if table exists.
//        Configuration configuration = HBaseConfiguration.create();
//        configuration.set("hbase.rootdir", "hdfs://192.168.15.137:9000/hbase");
//        configuration.set("hbase.zookeeper.quorum", "192.168.15.137");// zookeeper地址
//        configuration.set("hbase.zookeeper.property.clientPort", "2181");// zookeeper端口
//        HBaseAdmin hAdmin = new HBaseAdmin(configuration);
//        if (hAdmin.tableExists(tableName)) {
//            System.out.println("Table already exists, delete it!");
//            hAdmin.disableTable(TableName.valueOf(tableName));
//            hAdmin.deleteTable(TableName.valueOf(tableName));
//        }
//        HTableDescriptor htd = new HTableDescriptor(TableName.valueOf(tableName));
//        HColumnDescriptor cf = new HColumnDescriptor(COLUME_FAMILY);
//        htd.addFamily(cf);
//        hAdmin.createTable(htd);
//        hAdmin.close();
//        System.out.println("table " + tableName + " created successfully");
//
//        // put record
//        HTable table = new HTable(configuration, tableName);
//        for (String key : mapResult.keySet()) {
//            double[] currentRecord = mapResult.get(key);
//            String rowKey = key;
//            Put put = new Put(rowKey.getBytes());
//            for (int j = 0; j < currentRecord.length; j++) {
//                put.add(COLUME_FAMILY.getBytes(), res[j].getBytes(),
//                        (currentRecord[j] + "").getBytes());
//                table.put(put);
//            }
//        }
//        table.close();
//        System.out.println("put successfully");
//    }
//
//    public void processArgs(String[] argss) throws IOException, URISyntaxException {
//        String argStr0 = argss[0].replaceAll(" ", "");
//        String argStr1 = argss[1].replaceAll(" ", "");
//        String argStr2 = argss[2].replaceAll(" ", "");
//        // get the index of split point
//        int indexOfEqual0 = argStr0.indexOf('=');
//        int indexOfEqual1 = argStr1.indexOf(':');
//        int indexOfColon3 = argStr2.indexOf(':');
//
//        // get the right args, they are:
//        hdfsFile = HDFS_BASE_PATH + argStr0.substring(indexOfEqual0 + 1);
//        groupBy = Integer.parseInt(argStr1.substring(indexOfEqual1 + 2));
//        res = argStr2.substring(indexOfColon3 + 1).replace("'", "").split(",");
//
//    }
//
//    /**
//     * get args form terminal select columns by hash join and write the results into
//     * HBase.
//     */
//    public static void main(String[] args) throws IOException, URISyntaxException {
//        if (args.length != 3) {
//            System.out.println("Usage: java java Hw1GrpX R=<file> groupby:R2 'res:count,avg(R5),max(R0)'");
//            System.exit(1);
//        }
//
//        Hw1Grp2 hw1 = new Hw1Grp2();
//        hw1.processArgs(args);
//        // load HDFS file into inputstream
////        Configuration conf = new Configuration();
////        BufferedReader instream = HDFSReader(hdfsFile);
////        FileSystem fs = FileSystem.get(URI.create(hdfsFile), conf);
////        Path path = new Path(hdfsFile);
////        FSDataInputStream in_stream = fs.open(path);
////        BufferedReader in = new BufferedReader(new InputStreamReader(in_stream));
//        // in.close();
//        // fs.close();
////        return in;
//        hw1.groupBy(hdfsFile, groupBy, res);
//        hw1.writeHBase(TABLE_NAME);
////		System.out.println("Process Done!");
//    }
//}
