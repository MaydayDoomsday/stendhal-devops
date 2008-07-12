package games.stendhal.tools.loganalyser.util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

import org.apache.log4j.Logger;

/**
 * Iterates over a database query ResultSet-object doing all the
 * magic that is required to query a ResultSet.
 *
 * @author hendrik
 * @param <T> object type
 */
public abstract class ResultSetIterator<T> implements Iterator<T>, Iterable<T> {
	private static Logger logger = Logger.getLogger(ResultSetIterator.class);
	
	private final Statement statement;
	protected ResultSet resultSet;
	private boolean hasNext;
	private boolean nextCalled;
	private boolean closed;

	/**
	 * Creates a new ResultSetIterator.
	 *
	 * @param statement statement
	 * @param resultSet resultSet
	 */
	public ResultSetIterator(final Statement statement, final ResultSet resultSet) {
		this.statement = statement;
		this.resultSet = resultSet;
	}

	/**
	 * Creates the object instance.
	 *
	 * @return T
	 */
	protected abstract T createObject();

	public boolean hasNext() {
		if (nextCalled) {
			return hasNext;
		}
		nextCalled = true;
		resultSetNext();
		return hasNext;
	}

	/**
	 * calls resultSet.next without throwing an exception
	 * (errors are just logged and ignored).
	 */
	private void resultSetNext() {
		try {
	        hasNext = resultSet.next();
        } catch (final SQLException e) {
        	hasNext = false;
        	logger.error(e, e);
        }
        if (!hasNext) {
        	close();
        }
    }

	public T next() {
		if (!nextCalled) {
			resultSetNext();
		}
		nextCalled = false;
		return createObject();
	}

	public void remove() {
		try {
	        resultSet.deleteRow();
        } catch (final SQLException e) {
        	logger.error(e, e);
        }
	}

	/**
	 * Closes the resultSet and statement.
	 */
	private void close() {
		if (closed) {
			return;
		}
		closed = true;
		try {
			resultSet.close();
        } catch (final SQLException e) {
        	logger.error(e, e);
        }
		try {
			statement.close();
        } catch (final SQLException e) {
        	logger.error(e, e);
        }
	}

	public Iterator<T> iterator() {
		return this;
	}
	
}
