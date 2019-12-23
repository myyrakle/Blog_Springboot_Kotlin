package org.myyrakle.myblog.connector

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class MySQLConnector : IDBConnector {
    override var connection: Connection? = null
        private set

    override fun connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver")
            connection = DriverManager.getConnection(DB_URL + DB_NAME + DB_PARAMS, DB_USERNAME, DB_PASSWORD)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun disconnect() {
        try {
            connection!!.close()
            connection = null
        } catch (e: SQLException) {
            e.printStackTrace()
        }
    }

    override val isConnected: Boolean
        get() = connection != null

    companion object {
        private const val DB_NAME = ""
        private const val DB_URL = "jdbc:mysql://localhost:3306/"
        private const val DB_PARAMS = "?allowPublicKeyRetrieval=true&useSSL=false&useUnicode=true&characterEncoding=utf8"
        private const val DB_USERNAME = ""
        private const val DB_PASSWORD = ""
    }
}