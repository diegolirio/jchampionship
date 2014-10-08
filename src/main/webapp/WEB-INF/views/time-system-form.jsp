<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<form id="form" action="${pageContext.request.contextPath}/time/post">
	<h1 class="text-info"> Time</h1>
	
	<input type="hidden" value="${time.id}" name="id">
	
	<div class="form-group col-lg-4">
		<label>Nome do Time <small id="id_view" class="text-warning">?</small></label> <span id="id_message_nome"></span>
		<input class="form-control" type="text" name="nome">
	</div>						
 
	<br/>
		
	<a href="#" class="btn btn-default" onclick="window.close();">Cancelar</a>
	
	<a href="#" class="btn btn-success saveTime">Salvar</a>
		
	<br/><br/>
</form>

    <div class="row" id="id_tabelaJ">
        <div class="col-lg-12">
            <div class="panel panel-success"> 
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-fw fa-table"></i> Jogadores </h3>
                </div>
                <div class="panel-body">
	                               
					<a href="${pageContext.request.contextPath}/jogador/page/simple" class="btn btn-info pull-right addJogador">Adicionar Jogador</a>	                               
					<br/><br/> 
	                               
                    <!--  Table -->                                
					<table class="table table-striped table-hover well">
						<thead>
							<tr>
								<td title="ID">ID</td>
								<td >Nome</td>
								<td title="Excluir">#</td>
							</tr>
						<tbody id="id_tbody">
							<!--<center><img src="/media/images/load_32.gif"/></center>-->
						</tbody>								
					</table>                                
	                               
	                               
                </div>
            </div>
        </div>
    </div>


<script type="text/javascript" src="https://raw.githubusercontent.com/diegolirio/commons_js/master/ui-common.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/common-valid.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/quartashow/js/time-controller.js"></script>

