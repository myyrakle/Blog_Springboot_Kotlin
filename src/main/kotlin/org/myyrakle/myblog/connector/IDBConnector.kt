package org.myyrakle.myblog.connector

import java.sql.Connection

interface IDBConnector
{
    fun connect()
    fun disconnect()
    val isConnected: Boolean
    val connection: Connection?
}