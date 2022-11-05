package com.ratepay.bugtracker.utils;

import com.ratepay.bugtracker.model.Bug;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BugSpecificationBuilder {
    private final List<SearchCriteria> params;

    public BugSpecificationBuilder(){
        this.params = new ArrayList<>();
    }

    public final BugSpecificationBuilder with(String key, String operation, Object value){
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public final BugSpecificationBuilder with(SearchCriteria searchCriteria){
        params.add(searchCriteria);
        return this;
    }

    public Specification<Bug> build(){
        if(params.size() == 0){
            return null;
        }

        Specification<Bug> result = new BugSpecification(params.get(0));
        for (int idx = 1; idx < params.size(); idx++){
            SearchCriteria criteria = params.get(idx);
            result = SearchOperation.getDataOption(criteria.getDataOption()) == SearchOperation.ALL
                    ? Specification.where(result).and(new BugSpecification(criteria))
                    : Specification.where(result).or(new BugSpecification(criteria));
        }

        return result;
    }
}
