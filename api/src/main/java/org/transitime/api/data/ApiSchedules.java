/*
 * This file is part of Transitime.org
 * 
 * Transitime.org is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License (GPL) as published by
 * the Free Software Foundation, either version 3 of the License, or
 * any later version.
 *
 * Transitime.org is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Transitime.org .  If not, see <http://www.gnu.org/licenses/>.
 */

package org.transitime.api.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.transitime.ipc.data.IpcSchedule;

/**
 * Represents a collection of ApiSchedule objects for a route. There is one
 * ApiSchedule for each direction/service for a route.
 *
 * @author SkiBu Smith
 *
 */
@XmlRootElement(name = "schedules")
public class ApiSchedules {

	@XmlAttribute
	private String routeId;

	@XmlAttribute
	private String routeName;

	@XmlElement(name = "schedule")
	private List<ApiSchedule> schedules;
	
	/********************** Member Functions **************************/

	/**
	 * Need a no-arg constructor for Jersey. Otherwise get really obtuse
	 * "MessageBodyWriter not found for media type=application/json" exception.
	 */
	protected ApiSchedules() {
	}
	
	public ApiSchedules(List<IpcSchedule> schedules) {
		this.routeId = schedules.get(0).getRouteId();
		this.routeName = schedules.get(0).getRouteName();
		
		this.schedules = new ArrayList<ApiSchedule>(schedules.size());
		for (IpcSchedule ipcSchedule : schedules) {
			this.schedules.add(new ApiSchedule(ipcSchedule));
		}
	}
}
