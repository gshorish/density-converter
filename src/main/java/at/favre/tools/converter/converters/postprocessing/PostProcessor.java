/*
 * Copyright (C) 2016 Patrick Favre-Bulle
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
 *
 */

package at.favre.tools.converter.converters.postprocessing;

import java.io.File;

/**
 * PostProcessor run after the main conversation on all files
 */
public interface PostProcessor {

	/**
	 * Will process the given file. It is not necessary to spawn another thread for exectution
	 *
	 * @param rawFile to process
	 * @return optional log or output
	 */
	String process(File rawFile);
}
