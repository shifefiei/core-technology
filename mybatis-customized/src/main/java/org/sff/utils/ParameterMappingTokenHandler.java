package org.sff.utils;

import org.sff.sqlsource.ParameterMapping;

import java.util.ArrayList;
import java.util.List;

/**
 * 解析并存储了 sql 语句中的 所有参数
 */
public class ParameterMappingTokenHandler implements TokenHandler {

    /**
     * 包含 #{} 中参数名称和参数类型
     */
    private List<ParameterMapping> parameterMappings = new ArrayList<>();

    @Override
    public String handleToken(String content) {

        ParameterMapping parameterMapping = new ParameterMapping(content);
        parameterMappings.add(parameterMapping);
        return "?";
    }

    public List<ParameterMapping> getParameterMappings() {
        return parameterMappings;
    }

    public void setParameterMappings(List<ParameterMapping> parameterMappings) {
        this.parameterMappings = parameterMappings;
    }
}
