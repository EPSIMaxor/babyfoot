package com.babyfoot.maxor.babyfoot

import android.os.AsyncTask
import android.util.Log

import com.babyfoot.maxor.babyfoot.DBConnectionListener

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

/**
 * Data Access Object for your MS SQL Server. Just for a note that, though it is
 * correct to maintain a connection object to remote server. It is not a good
 * design. Rather a better approach is to implement your RESTApi and call it. It
 * your choice. Some people just want output, others give importance to software
 * design , like me.
 *
 * This class is a singleton,
 *
 * To use this class,
 *
 * Dao dao = Dao.instance(DBConnectionListener); dao.connect(...);
 *
 * Once you receive a call back DBConnectionListener.onConnectionSuccessful()
 *
 * you can start using your db operations like dao.addUser()
 *
 * When the activity is destroyed call
 *
 * dao.disconnect()
 *
 * @author Althaf K Backer <althafkbacker></althafkbacker>@gmail.com>
 */
class Dao private constructor()/* Singleton */ {
    private var dbConnectionListener: DBConnectionListener? = null
    private var serverAddress: String? = null
    private var password: String? = null
    private var username: String? = null
    private var dbName: String? = null

    /**
     * All network related task should be kept away from UI thread. Ensure that
     * you have the permission setup in the AndroidManifest.xml <uses-permission android:name="android.permission.INTERNET"></uses-permission>
     */
    private inner class DBConnectionTask : AsyncTask<Void, Void, Void>() {

        private val LOG_TAG = DBConnectionTask::class.java.name

        override fun doInBackground(vararg arg: Void): Void? {

            try {

                Log.i(LOG_TAG, "attempting to connect to " + serverAddress!!)
                dbConnectionListener!!
                        .onConnectionStatusInfo("attempting to connect to " + serverAddress!!)
                Log.i(LOG_TAG, "with username " + username!!)
                dbConnectionListener!!.onConnectionStatusInfo("with username " + username!!)
                Log.i(LOG_TAG, "with password " + password!!)
                dbConnectionListener!!.onConnectionStatusInfo("with password " + password!!)

                Class.forName("net.sourceforge.jtds.jdbc.Driver")
                conn = DriverManager.getConnection("jdbc:jtds:sqlserver://"
                        + serverAddress + "/" + dbName, username, password)

                Log.i(LOG_TAG, "connected ")
                dbConnectionListener!!.onConnectionStatusInfo("[*] connected ")
                dbConnectionListener!!.onConnectionSuccessful()

            } catch (e: Exception) {

                Log.i(LOG_TAG, "connecting failed")
                dbConnectionListener!!
                        .onConnectionStatusInfo("[x] connecting failed")
                dbConnectionListener!!.onConnectionFailed()
                e.printStackTrace()
                Log.e(LOG_TAG, e.message)

            }

            return null
        }
    }

    /**
     * Should be called before calling Dao operations like addUser()
     *
     * @param serverAddress
     * @param password
     * @param username
     * @param dbName
     */

    fun connect(serverAddress: String, password: String, username: String,
                dbName: String) {

        this.serverAddress = serverAddress
        this.dbName = dbName
        this.username = username
        this.password = password

        if (conn == null) {

            DBConnectionTask().execute(null, null, null)

        } else {

            dbConnectionListener!!.onConnectionSuccessful()

        }

    }

    /**
     * Call when your activity is destroyed
     */
    fun disconnect() {

        if (conn != null) {

            try {

                if (!conn!!.isClosed) {

                    conn!!.close()
                    Log.i(LOG_TAG, "db connection closed")
                    dbConnectionListener!!
                            .onConnectionStatusInfo("db connection closed")

                }

            } catch (e: SQLException) {

                e.printStackTrace()
                Log.e(LOG_TAG, "db connection failed during close()")
                dbConnectionListener!!
                        .onConnectionStatusInfo("db connection failed to close() ")

            } finally {

                conn = null

            }

        }

    }

    companion object {

        private val LOG_TAG = Dao::class.java.name
        private var conn: Connection? = null
        private val singletonObj = Dao()

        fun instance(listener: DBConnectionListener): Dao {

            singletonObj.dbConnectionListener = listener
            return singletonObj

        }
    }

    /**
     * This is a stub and not a functional one, of how to use your new conn
     * object, connected to your mssql server. More Info :
     * http://developer.android.com/reference/java/sql/package-summary.html
     */
    //    public void addUser(/* Some user info Model as parameter */) {
    //
    //        Log.i(LOG_TAG, "in adduser()");
    //
    //        if (conn == null) {
    //
    //            throw new InstantiationError(
    //                    "call Dao.connect(...) before calling Dao operations");
    //
    //        }
    //
    //        try {
    //
    //            Statement statement = conn.createStatement();
    //            ResultSet rs = statement
    //                    .executeQuery("INSERT INTO user_info_table "
    //                            + " VALUES ('1001', 'Bob', '333333', '33')");
    //            rs.close();
    //            statement.close();
    //
    //        } catch (SQLException e) {
    //
    //            e.printStackTrace();
    //
    //        }
    //
    //    }
}