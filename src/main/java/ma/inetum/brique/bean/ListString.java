package ma.inetum.brique.bean;

import java.util.LinkedList;
import java.util.List;

public class ListString {
	private List<String> header = new LinkedList<>();
	private List<List<String>> details = new LinkedList<>();
	public List<String> getHeader() {
		return header;
	}
	public void setHeader(List<String> header) {
		this.header = header;
	}
	public List<List<String>> getDetails() {
		return details;
	}
	public void setDetails(List<List<String>> details) {
		this.details = details;
	}
}
