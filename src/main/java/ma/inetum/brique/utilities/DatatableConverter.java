package ma.inetum.brique.utilities;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.parameter.ColumnParameter;
import org.springframework.data.jpa.datatables.parameter.OrderParameter;
import org.springframework.data.jpa.datatables.parameter.SearchParameter;

public class DatatableConverter {
	private static final String BLANK = "";

	/** The Constant SPACE. */
	private static final String SPACE = " ";

	/** The Constant LIKE_PREFIX. */
	private static final String LIKE_PREFIX = " LIKE '%";

	/** The Constant LIKE_SUFFIX. */
	private static final String LIKE_SUFFIX = "%' ";

	/** The Constant AND. */
	private static final String AND = " AND ";

	/** The Constant OR. */
	private static final String OR = " OR ";

	/** The Constant ORDER_BY. */
	private static final String ORDER_BY = " ORDER BY ";

	private static final String BRKT_OPN = " ( ";

	private static final String BRKT_CLS = " ) ";

	/** The Constant COMMA. */
	private static final String COMMA = " , ";

	/** The Constant PAGE_NO. */
	public static final String PAGE_NO = "start";

	/** The Constant PAGE_SIZE. */
	public static final String PAGE_SIZE = "length";

	/** The Constant DRAW. */
	public static final String DRAW = "draw";

	public static DataTablesInput convert(HttpServletRequest request) {
		DataTablesInput input = new DataTablesInput();
		Enumeration<String> parameterNames = request.getParameterNames();
		
		if (parameterNames.hasMoreElements()) {

			input.setStart(Integer.parseInt(request.getParameter(PAGE_NO)));
			input.setLength(Integer.parseInt(request.getParameter(PAGE_SIZE)));
			input.setDraw(Integer.parseInt(request.getParameter(DRAW)));
			SearchParameter search= new SearchParameter();
			search.setRegex(Boolean.valueOf(request.getParameter("search[regex]")));
			search.setValue(request.getParameter("search[value]"));
			input.setSearch(search);
			List<OrderParameter> orders = new ArrayList<>();
				orders.add(new OrderParameter(Integer.parseInt(request.getParameter("order[0][column]")), request.getParameter("order[0][dir]")));
			input.setOrder(orders);
			List<ColumnParameter> columns = new ArrayList<>();
			
			
			input.setColumns(columns);
			
			int maxParamsToCheck = getNumberOfColumns(request);

			for (int i = 0; i < maxParamsToCheck; i++) {
				if (null != request.getParameter("columns[" + i + "][data]")
						&& !"null".equalsIgnoreCase(request.getParameter("columns[" + i + "][data]"))
						&& !request.getParameter("columns[" + i + "][data]").isBlank()) {
					ColumnParameter column = new ColumnParameter();
					column.setData(request.getParameter("columns["+ i +"][data]"));
					column.setName(request.getParameter("columns["+ i +"][name]"));
					column.setOrderable(Boolean.valueOf(request.getParameter("columns["+ i +"][orderable]")));
					column.setSearchable(Boolean.valueOf(request.getParameter("columns["+ i +"][searchable]")));
					column.setSearch(new SearchParameter(request.getParameter("columns["+ i +"][search][value]"), Boolean.valueOf(request.getParameter("columns["+ i +"][search][regex]"))));
					column.setSearchable(Boolean.valueOf(request.getParameter("columns["+ i +"][searchable]")));
					columns.add(column);
				}
			}
			input.setColumns(columns);
		}
		return input;
	}
	private static int getNumberOfColumns(HttpServletRequest request) {
		Pattern p = Pattern.compile("columns\\[[0-9]+\\]\\[data\\]");  
		@SuppressWarnings("rawtypes")
		Enumeration params = request.getParameterNames(); 
		List<String> lstOfParams = new ArrayList<String>();
		while(params.hasMoreElements()){		
		 String paramName = (String)params.nextElement();
		 Matcher m = p.matcher(paramName);
		 if(m.matches())	{
			 lstOfParams.add(paramName);
		 }
		}
		return lstOfParams.size();
	}
}
