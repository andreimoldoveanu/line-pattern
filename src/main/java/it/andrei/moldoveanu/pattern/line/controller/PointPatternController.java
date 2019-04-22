/*******************************************************************************
 * Copyright 2017 The MIT Internet Trust Consortium
 *
 * Portions copyright 2011-2013 The MITRE Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package it.andrei.moldoveanu.pattern.line.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import it.andrei.moldoveanu.pattern.line.map.PatternMap;
import it.andrei.moldoveanu.pattern.line.map.Point;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping("/point/pattern")
public class PointPatternController {

	// log purposes
	private static final Logger logger = LoggerFactory.getLogger(PointPatternController.class);

	/**
	 * Add point in graph
	 * 
	 * @param Point
	 *            point
	 * @return "OK", "KO"
	 */
	@RequestMapping(value = "/point", method = RequestMethod.POST, produces = "application/json")
	public @ResponseBody String addPoint(@RequestBody Point point) {
		return PatternMap.addPoint(point);
	}

	/**
	 * Get all points in the space
	 * 
	 * @return List of points
	 */
	@RequestMapping(value = "/space", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody List<Point> getSpace() {
		return PatternMap.getSpace();
	}

	/**
	 * Return pattern with the minimum "n" points in the line
	 * 
	 * @param number
	 *            = minimum points on the line
	 * @return map of filtered lines
	 */
	@RequestMapping(value = "/lines", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody Map<Object, Object> getPatterns(@RequestParam(value = "n") Integer number) {
		if (number < 0)
			return null;
		return PatternMap.getPatterns(number);
	}

	/**
	 * Delete all the points in the space
	 * 
	 */
	@RequestMapping(value = "/space", method = RequestMethod.DELETE)
	public @ResponseBody String deleteSpace() {
		PatternMap.deleteSpace();
		return "OK";
	}

}
