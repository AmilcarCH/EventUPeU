package pe.edu.amilcarch.dbcon;

import android.content.Context;

import pe.edu.upeu.dbexterno.ExternalSQLiteOpenHelper;

public class DBconn extends ExternalSQLiteOpenHelper{

    private static final String DATABASE_NAME="asistenciadb.db";
    private static final int DATABASE_VERSION=1;

    public DBconn(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
}

