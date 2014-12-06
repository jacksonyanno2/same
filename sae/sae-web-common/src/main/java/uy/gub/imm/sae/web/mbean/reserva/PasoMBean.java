/*
 * SAME - Sistema de Gestion de Turnos por Internet
 * SAME is a fork of SAE - Sistema de Agenda Electronica
 * 
 * Copyright (C) 2009  IMM - Intendencia Municipal de Montevideo
 * Copyright (C) 2013, 2014  SAGANT - Codestra S.R.L.
 * Copyright (C) 2013, 2014  Alvaro Rettich <alvaro@sagant.com>
 * Copyright (C) 2013, 2014  Carlos Gutierrez <carlos@sagant.com>
 * Copyright (C) 2013, 2014  Victor Dumas <victor@sagant.com>
 *
 * This file is part of SAME.

 * SAME is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package uy.gub.imm.sae.web.mbean.reserva;

import uy.gub.imm.sae.web.common.BaseMBean;

public abstract class PasoMBean	extends BaseMBean{
	
	/**
	 * Outcome que deben estar configurados 
	 * en las reglas de navegacion del faces-config
	 */
	static protected String ESTADO_INVALIDO_PAGE_OUTCOME = "estado_invalido";
	static protected String PERIODO_INVALIDO_PAGE_OUTCOME = "periodo_invalido";


	protected SesionMBean sesionMBean;
	
	public SesionMBean getSesionMBean() {
		return sesionMBean;
	}

	public void setSesionMBean(SesionMBean sesionMBean) {
		this.sesionMBean = sesionMBean;
	}

	public String getAgendaLogo() {
		
		//Base64Encoder b64 = new Base64Encoder(new String(sesionMBean.getAgenda().getLogo()));
		byte[] logo = sesionMBean.getAgenda().getLogo();
		if (logo == null || logo.length == 0) {
			
			return new String("data:image/png;base64,"+  "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");
		
		} else {
			return new String(logo);
		}

	}
}