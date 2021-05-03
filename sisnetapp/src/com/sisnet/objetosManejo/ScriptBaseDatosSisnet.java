package com.sisnet.objetosManejo;
import com.sisnet.constantes.ConstantesGeneral;
import com.sisnet.objetosManejo.listas.ListaCadenas;
public class ScriptBaseDatosSisnet
{
  public ListaCadenas obtenerListadoInstruccionesSqlBaseDatosSisnetVersion01() {
    ListaCadenas listaInstrucciones_local = null;
    try {
      listaInstrucciones_local = new ListaCadenas();
      adicionarListadoInstruccionesSqlAplicacion(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlGrupoInformacion(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlCampo(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlValorDependiente(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlTabla(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlUsuario(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlUsuarioAutorizado(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlPermisoUsuario(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlConfiguracion(listaInstrucciones_local);
      
      adicionarListadoInstruccionesSqlIndicesSisnet(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlLlavesPrimariasSisnet(listaInstrucciones_local);
      adicionarListadoInstruccionesSqlLlavesForaneasSisnet(listaInstrucciones_local);
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
    return listaInstrucciones_local;
  }
  private void adicionarListadoInstruccionesSqlAplicacion(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pListaInstrucciones.adicionar("create table aplicacion ( fldidaplicacion integer not null );");
      pListaInstrucciones.adicionar("alter table aplicacion add fldnombreaplicacion varchar(30) not null;");
      pListaInstrucciones.adicionar("alter table aplicacion add fldtituloaplicacion varchar(100) not null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlGrupoInformacion(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pListaInstrucciones.adicionar("create table grupoinformacion( fldidgrupoinformacion integer not null );");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldidaplicacion integer not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldnombregrupoinformacion varchar(30) not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add flddescripciongrupoinformacion varchar(100) not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldcolorgrupoinformacion varchar(7) not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldgrupoinformacionprincipal bool not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldgrupoinformacionmultiple bool not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldinclusionobligatoria bool not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldgrupoinformacionpredeterminado bool not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldposicion int not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldrepeticionultimoregistro bool not null;");
      pListaInstrucciones.adicionar("alter table grupoinformacion add fldexigiralerta bool;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlTabla(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pListaInstrucciones.adicionar("create table tabla( fldidtabla integer not null );");
      pListaInstrucciones.adicionar("alter table tabla add fldidaplicacion integer not null");
      pListaInstrucciones.adicionar("alter table tabla add fldnombretabla varchar(30) not null;");
      pListaInstrucciones.adicionar("alter table tabla add flddescripciontabla varchar(50) not null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlCampo(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pListaInstrucciones.adicionar("create table campo ( fldidcampo integer not null );");
      pListaInstrucciones.adicionar("alter table campo add fldidgrupoinformacion integer not null;");
      pListaInstrucciones.adicionar("alter table campo add fldnombrecampo varchar(30) not null;");
      pListaInstrucciones.adicionar("alter table campo add fldetiquetacampo varchar(50) not null;");
      pListaInstrucciones.adicionar("alter table campo add fldtipodato varchar(3) not null;");
      pListaInstrucciones.adicionar("alter table campo add fldvalorunico bool not null;");
      pListaInstrucciones.adicionar("alter table campo add fldlongitudcampo integer;");
      pListaInstrucciones.adicionar("alter table campo add fldnumerodecimales integer;");
      pListaInstrucciones.adicionar("alter table campo add fldformatocampo varchar(15);");
      pListaInstrucciones.adicionar("alter table campo add fldvalordefecto varchar(50);");
      pListaInstrucciones.adicionar("alter table campo add fldvalorminimo varchar(50);");
      pListaInstrucciones.adicionar("alter table campo add fldvalormaximo varchar(50);");
      pListaInstrucciones.adicionar("alter table campo add fldllaveprimaria bool not null;");
      pListaInstrucciones.adicionar("alter table campo add fldllaveforanea bool not null;");
      pListaInstrucciones.adicionar("alter table campo add fldobligatorio bool not null;");
      pListaInstrucciones.adicionar("alter table campo add fldvisibleusuario bool not null;");
      pListaInstrucciones.adicionar("alter table campo add fldidcampodepende integer;");
      pListaInstrucciones.adicionar("alter table campo add fldmodificable bool not null;");
      pListaInstrucciones.adicionar("alter table campo add fldposicion int not null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlUsuario(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pListaInstrucciones.adicionar("create table usuario ( fldidusuario integer not null );");
      pListaInstrucciones.adicionar("alter table usuario add fldnombreusuario varchar(15) not null;");
      pListaInstrucciones.adicionar("alter table usuario add fldcontrasena varchar(32) not null;");
      pListaInstrucciones.adicionar("alter table usuario add fldnumeroaleatorio integer not null;");
      pListaInstrucciones.adicionar("alter table usuario add fldtipolicencia varchar(1) not null;");
      pListaInstrucciones.adicionar("alter table usuario add fldtipousuario varchar(1) not null;");
      pListaInstrucciones.adicionar("alter table usuario add fldfechavencimiento date not null;");
      pListaInstrucciones.adicionar("alter table usuario add fldusuariosautorizables integer not null;");
      pListaInstrucciones.adicionar("alter table usuario add fldnumeroaplicaciones integer not null;");
      pListaInstrucciones.adicionar("alter table usuario add fldlicencia varchar(32);");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlUsuarioAutorizado(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pListaInstrucciones.adicionar("create table usuarioautorizado ( fldidusuarioautorizado integer not null );");
      pListaInstrucciones.adicionar("alter table usuarioautorizado add fldidusuarioautorizadousuario integer not null;");
      pListaInstrucciones.adicionar("alter table usuarioautorizado add fldidusuario integer not null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlPermisoUsuario(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pListaInstrucciones.adicionar("create table permisousuario( fldidpermisousuario integer not null );");
      pListaInstrucciones.adicionar("alter table permisousuario add fldidusuarioautorizado integer not null;");
      pListaInstrucciones.adicionar("alter table permisousuario add fldidgrupoinformacion integer not null;");
      pListaInstrucciones.adicionar("alter table permisousuario add fldaccionpermitida varchar(1) not null;");
      pListaInstrucciones.adicionar("alter table permisousuario add fldvalorpermiso bool not null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlValorDependiente(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pListaInstrucciones.adicionar("create table valordependiente ( fldidvalordependiente integer not null );");
      pListaInstrucciones.adicionar("alter table valordependiente add fldidcampo integer not null;");
      pListaInstrucciones.adicionar("alter table valordependiente add fldidvalormaestro integer not null;");
      pListaInstrucciones.adicionar("alter table valordependiente add fldidvalordetalle integer not null;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlIndicesSisnet(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pListaInstrucciones.adicionar("create unique index idxaplicacion on aplicacion (fldnombreaplicacion);");
      pListaInstrucciones.adicionar("create unique index idxtabla on tabla (fldnombretabla)");
      pListaInstrucciones.adicionar("create unique index idxgrupoinformacion on grupoinformacion (fldidaplicacion, fldnombregrupoinformacion);");
      pListaInstrucciones.adicionar("create unique index idxcampo on campo (fldidgrupoinformacion, fldnombrecampo);");
      pListaInstrucciones.adicionar("create unique index idxusuario on usuario (fldnombreusuario);");
      pListaInstrucciones.adicionar("create unique index idxvalordependiente on valordependiente (fldidcampo, fldidvalormaestro, fldidvalordetalle);");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlLlavesPrimariasSisnet(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pListaInstrucciones.adicionar("alter table aplicacion add constraint pkaplicacion primary key (fldidaplicacion);");
      pListaInstrucciones.adicionar("alter table grupoinformacion add constraint pkgrupoinformacion primary key (fldidgrupoinformacion);");
      pListaInstrucciones.adicionar("alter table tabla add constraint pktabla primary key (fldidtabla);");
      pListaInstrucciones.adicionar("alter table campo add constraint pkcampo primary key (fldidcampo);");
      pListaInstrucciones.adicionar("alter table valordependiente add constraint pkvalordependiente primary key (fldidvalordependiente);");
      pListaInstrucciones.adicionar("alter table usuario add constraint pkusuario primary key (fldidusuario);");
      pListaInstrucciones.adicionar("alter table usuarioautorizado add constraint pkusuarioautorizado primary key (fldidusuarioautorizado);");
      pListaInstrucciones.adicionar("alter table permisousuario add constraint pkpermisousuario primary key (fldidpermisousuario);");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlLlavesForaneasSisnet(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    
    try {
      pListaInstrucciones.adicionar("alter table tabla add constraint fktabla foreign key (fldidaplicacion) references aplicacion(fldidaplicacion) on update cascade on delete cascade");
      
      pListaInstrucciones.adicionar("alter table grupoinformacion add constraint fkgrupoinformacion foreign key (fldidaplicacion) references aplicacion(fldidaplicacion) on update cascade on delete cascade;");
      
      pListaInstrucciones.adicionar("alter table campo add constraint fkcampo foreign key (fldidgrupoinformacion)  references grupoinformacion(fldidgrupoinformacion) on update cascade on delete cascade;");
      
      pListaInstrucciones.adicionar("alter table valordependiente add constraint fkvalordependientecampo foreign key (fldidcampo)references campo(fldidcampo) on update cascade on delete cascade;");
      
      pListaInstrucciones.adicionar("alter table permisousuario add constraint fkpermisousuarioautorizado foreign key  (fldidusuarioautorizado) references usuarioautorizado(fldidusuarioautorizado) on update cascade on delete cascade;");
      
      pListaInstrucciones.adicionar("alter table permisousuario add constraint fkpermisosusuariogrupo foreign key (fldidgrupoinformacion) references grupoinformacion(fldidgrupoinformacion) on update cascade on delete cascade;");
      
      pListaInstrucciones.adicionar("alter table usuarioautorizado add constraint fkusuarioautorizado foreign key (fldidusuario)  references usuario(fldidusuario) on update cascade on delete cascade;");
    }
    catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
  private void adicionarListadoInstruccionesSqlConfiguracion(ListaCadenas pListaInstrucciones) {
    if (pListaInstrucciones == ConstantesGeneral.VALOR_NULO) {
      return;
    }
    try {
      pListaInstrucciones.adicionar("create table configuracion (fldnumeroversion integer not null);");
      pListaInstrucciones.adicionar("alter table configuracion add fldfechaversion date;");
    } catch (Exception excepcion) {
      excepcion.printStackTrace();
    } 
  }
}
/* Location:              D:\Personal\sisnet\sisnetMasterApp\sisnetapp.war!\WEB-INF\classes\com\sisnet\objetosManejo\ScriptBaseDatosSisnet.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */