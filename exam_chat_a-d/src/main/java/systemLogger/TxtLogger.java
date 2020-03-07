package systemLogger;

import java.time.LocalDateTime;

import communication.Communicator;
import systemLogger.Logger;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class TxtLogger implements Logger{ 

	private static final StringBuilder SB = new StringBuilder();
	private final String _fileName;
	private boolean _state;


	public TxtLogger(String fileName) {
		_fileName = fileName;
		_state = true;
	}


}
