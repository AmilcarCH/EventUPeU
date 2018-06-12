package pe.edu.amilcarch.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


import pe.edu.amilcarch.dbcon.DBconn;
import pe.edu.amilcarch.to.EventoTO;

public class EventoDao extends DBconn {

    DBconn con;
    Context context;
    SQLiteDatabase db;
    Cursor cur;
    String sql;

    public EventoDao(Context context) {
        super(context);
        this.context=context;
    }

    public List ListarEvento(){
        con=new DBconn(context);
        db=con.getReadableDatabase();
        sql="select * from evento";
        cur=db.rawQuery(sql,null);
        ArrayList<EventoTO> lista=new ArrayList<EventoTO>();
        EventoTO to=null;
        while(cur.moveToNext()){
            to=new EventoTO();
            to.setNombreevento(cur.getString(4));
            to.setLugarevento(cur.getString(5));
            to.setLongitud(cur.getInt(8));
            to.setLatitud(cur.getInt(9));
            lista.add(to);
        }
        return lista;
    }
    public int eventoActivo(){
        con=new DBconn(context);
        db=con.getReadableDatabase();
        sql=" select * from evento where estado='1' ";
        cur=db.rawQuery(sql, null);
        if(cur.moveToNext()){
            return cur.getInt(0);
        }else{
            return 0;
        }
    }

    public void cambiarEstadoEvento(int idEvento){
        con=new DBconn(context);
        db=con.getWritableDatabase();
        db.execSQL("update evento set estado='0' ");
        db.execSQL("update evento set estado='1' where idEvento="+idEvento+" ");
    }
}
