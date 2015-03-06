/**
 * Copyright (C) 2015
 * by 52 North Initiative for Geospatial Open Source Software GmbH
 *
 * Contact: Andreas Wytzisk
 * 52 North Initiative for Geospatial Open Source Software GmbH
 * Martin-Luther-King-Weg 24
 * 48155 Muenster, Germany
 * info@52north.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.n52.wps.csv2wiki;

import java.io.IOException;

import net.opengis.wps.x100.ProcessDescriptionType;

import org.n52.wps.algorithm.annotation.Algorithm;
import org.n52.wps.algorithm.annotation.ComplexDataInput;
import org.n52.wps.algorithm.annotation.Execute;
import org.n52.wps.algorithm.annotation.LiteralDataOutput;
import org.n52.wps.io.data.binding.complex.PlainStringBinding;
import org.n52.wps.server.AbstractAnnotatedAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author matthes rieke
 *
 */
@Algorithm(
		version = "0.1",
		abstrakt = "CSV 2 TWiki table Algorithm",
		title = "CSV2TWikiAlgorithm",
		//identifier = "your-identifer",
		statusSupported = false,
		storeSupported = false)
public class CSV2TWikiAlgorithm extends AbstractAnnotatedAlgorithm {
	
	@Override
	public synchronized ProcessDescriptionType getDescription() {
		return super.getDescription();
	}

	private static final Logger logger = LoggerFactory
			.getLogger(CSV2TWikiAlgorithm.class);
	private String output;
	
	@ComplexDataInput(
			identifier = "csvString", binding = PlainStringBinding.class)
	public String input;
	
	@LiteralDataOutput(identifier = "twikiTableString")
	public String getOutput() {
		return this.output;
	}
	
	@Execute
	public void execute() throws IOException {
		logger.info("processing input: "+this.input);
		CSV2TWikiProcess p = new CSV2TWikiProcess();
		this.output = p.transform(this.input);
	}

}
