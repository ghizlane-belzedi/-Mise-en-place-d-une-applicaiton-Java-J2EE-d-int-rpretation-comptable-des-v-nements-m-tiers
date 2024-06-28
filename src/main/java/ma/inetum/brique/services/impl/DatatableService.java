package ma.inetum.brique.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.inetum.brique.model.principale.datatable.PaginationCriteria;

public abstract class DatatableService<T> {
	private ObjectMapper objectMapper = new ObjectMapper();

    public List<Map<String, String>> getPageEntries(PaginationCriteria paginationCriteria) throws RuntimeException {
        List<T> data = getAll(paginationCriteria);
        
        List<Map<String, String>> records = new ArrayList<>(data.size());
        try {
            data.forEach(i -> {
                Map<String, Object> m = objectMapper.convertValue(i, Map.class);
                records.add(m.entrySet().stream()
                        .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().toString())));
            });
        } catch (Exception e) {
            throw new RuntimeException("", e);
        }
        return records;
    }

    protected abstract List<T> getAll(PaginationCriteria paginationCriteria) throws RuntimeException;
}
