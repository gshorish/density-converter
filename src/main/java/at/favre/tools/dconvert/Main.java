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

package at.favre.tools.dconvert;

import at.favre.tools.dconvert.arg.Arguments;
import at.favre.tools.dconvert.ui.CLInterpreter;
import at.favre.tools.dconvert.ui.GUI;

import java.util.List;
import java.util.Locale;

/**
 * Entry point of the app
 */
public class Main {
	public static void main(String[] rawArgs) {

		if (rawArgs.length < 1) {
			new GUI().launchApp(rawArgs);
			return;
		}

		Arguments args = CLInterpreter.parse(rawArgs);

		if (args == null) {
			return;
		} else if (args == Arguments.START_GUI) {
			System.out.println("start gui");
			new GUI().launchApp(rawArgs);
			return;
		}

		new ConverterHandler().execute(args, new ConverterHandler.HandlerCallback() {
			int i = 0;
			@Override
			public void onProgress(float progress) {
				i++;
				System.out.print(String.format(Locale.US, "%.2f", progress * 100f) + "%\t");
				if (i % 10 == 0) {
					System.out.println("");
				}
			}

			@Override
			public void onFinished(int finishedJobs, List<Exception> exceptions, long time, boolean haltedDuringProcess, String log) {

				System.out.println("");

				if (args.verboseLog) {
					System.out.println("Log:");
					System.out.println(log);
				}

				if (haltedDuringProcess) {
					System.err.println("abort due to error");
				}
				if (exceptions.size() > 0) {
					System.err.println("found " + exceptions.size() + " errors during execution");
					if (args.verboseLog) {
						for (Exception exception : exceptions) {
							System.err.println("\terror: " + exception.getMessage());
							exception.printStackTrace();
						}
					}
				}
				System.out.println("execution finished (" + time + "ms) with " + finishedJobs + " finsihed jobs and " + exceptions.size() + " errors");
			}
		}, true);
	}
}
