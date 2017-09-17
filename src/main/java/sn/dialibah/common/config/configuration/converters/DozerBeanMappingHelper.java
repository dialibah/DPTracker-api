package sn.dialibah.common.config.configuration.converters;

import lombok.*;
import org.dozer.CustomConverter;
import org.dozer.loader.api.BeanMappingBuilder;
import org.dozer.loader.api.TypeMappingBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.dozer.loader.api.FieldsMappingOptions.customConverter;


/**
 * Created by nureynisow on 17/09/2017.
 * for DPTracker
 */

@Getter
@Setter(AccessLevel.PROTECTED)
public class DozerBeanMappingHelper {

    private Class<?> inputClass;
    private Class<?> outputClass;
    private List<FieldMappingRelation> fieldMappingRelations;

    @Data
    @AllArgsConstructor
    protected class FieldMappingRelation {
        private String inputField;
        private String outputField;
        private Class<? extends CustomConverter> converterClass;
    }

    public static DozerBeanMappingHelper builder() {
        return new DozerBeanMappingHelper();
    }

    /**
     * Build a BeanMappingBuilder that map the field {@code fieldName} with the converter {@code converterClass}
     * for the same class {@code targetClass}
     *
     * @param targetClass    The class which contains the property to map. This class is BOTH the input AND the output class
     * @param fieldName      The field name to map. Since the input class is the same as the output class, the input field and the output fields are the same.
     * @param converterClass The {@code Class} object to use to convert the field
     * @return a {@code BeanMappingBuilder} that can be registered into Dozer
     */
    public static BeanMappingBuilder buildSameClassMapper(Class<?> targetClass,
                                                          String fieldName,
                                                          Class<? extends CustomConverter> converterClass) {
        return new DozerBeanMappingHelper()
                .setClasses(targetClass)
                .addSameTypeMappingRelation(fieldName, converterClass)
                .build();
    }

    /**
     * Build a BeanMappingBuilder that map the field {@code fieldName} with the converter {@code converterClass}
     * for the same class {@code targetClass}
     *
     * @param targetClass    The class which contains the property to map. This class is BOTH the input AND the output class
     * @param fieldNames     A list of field names to map. Since the input class is the same as the output class,
     *                       each field name in the @{code fieldNames} parameter refer to the same field in the class.
     * @param converterClass The {@code Class} object to use to convert all the fields
     * @return a {@code BeanMappingBuilder} that can be registered into Dozer
     */
    public static BeanMappingBuilder buildSameClassMapper(Class<?> targetClass,
                                                          List<String> fieldNames,
                                                          Class<? extends CustomConverter> converterClass) {
        final DozerBeanMappingHelper helper = new DozerBeanMappingHelper();
        helper.setClasses(targetClass);
        fieldNames.forEach((fieldName) -> helper.addSameTypeMappingRelation(fieldName, converterClass));
        return helper.build();
    }

    /**
     * Build a BeanMappingBuilder that map the field {@code fieldName} with the converter {@code converterClass}
     * between classes @{code inputClass} and @{code outputClass}
     *
     * @param inputClass     the input class for the mapping. Note that since all builders are bi directional,
     *                       the order between @{code inputClass} and @{code outputClass} does not matter
     * @param outputClass    the output class for the mapping. Note that since all builders are bi directional,
     *                       the order between @{code inputClass} and @{code outputClass} does not matter
     * @param fieldName      the name of the field to map. The field name MUST be the same in @{code inputClass} and @{code outputClass}
     * @param converterClass The {@code Class} object to use to convert the field
     * @return
     */
    public static BeanMappingBuilder buildClassMapper(Class<?> inputClass, Class<?> outputClass,
                                                      String fieldName,
                                                      Class<? extends CustomConverter> converterClass) {
        return new DozerBeanMappingHelper()
                .setClasses(inputClass, outputClass)
                .addSameTypeMappingRelation(fieldName, converterClass)
                .build();
    }

    /**
     * Build a BeanMappingBuilder that map all the fields in {@code fieldNames} with the converter {@code converterClass}
     * between classes @{code inputClass} and @{code outputClass}
     *
     * @param inputClass     the input class for the mapping. Note that since all builders are bi directional,
     *                       the order between @{code inputClass} and @{code outputClass} does not matter
     * @param outputClass    the output class for the mapping. Note that since all builders are bi directional,
     *                       the order between @{code inputClass} and @{code outputClass} does not matter
     * @param fieldNames     a list of field names to map. Each field name MUST be the same in @{code inputClass} and @{code outputClass}
     * @param converterClass The {@code Class} object to use to convert all the fields
     * @return
     */
    public static BeanMappingBuilder buildClassMapper(Class<?> inputClass, Class<?> outputClass,
                                                      List<String> fieldNames,
                                                      Class<? extends CustomConverter> converterClass) {
        final DozerBeanMappingHelper helper = new DozerBeanMappingHelper();
        helper.setClasses(inputClass, outputClass);
        fieldNames.forEach((fieldName) -> helper.addSameTypeMappingRelation(fieldName, converterClass));
        return helper.build();
    }

    /**
     * Default constructor
     */
    public DozerBeanMappingHelper() {
        this.fieldMappingRelations = new ArrayList<>();
    }

    /**
     * Set the target class for both input and output
     *
     * @param inputOutput A {@code Class} object that represents the input and output class
     * @return the same instance of {@code DozerBeanMappingHelper}
     */
    public DozerBeanMappingHelper setClasses(Class<?> inputOutput) {
        setClasses(inputOutput, inputOutput);
        return this;
    }

    /**
     * Set the target classes for both input and output
     *
     * @param input  A {@code Class} object that represents the input class
     * @param output A {@code Class} object that represents the output class
     * @return the same instance of {@code DozerBeanMappingHelper}
     */
    public DozerBeanMappingHelper setClasses(Class<?> input, Class<?> output) {
        setInputClass(input);
        setOutputClass(output);
        return this;
    }

    /**
     * adds a mapping between two properties.
     *
     * @param inputField     the field name in the input class
     * @param outputField    the field name in the output class
     * @param converterClass the {@code Class} object representing the converter to use for the mapping
     * @return the same instance of {@code DozerBeanMappingHelper}
     */
    public DozerBeanMappingHelper addTypeMappingRelation(String inputField, String outputField,
                                                         Class<? extends CustomConverter> converterClass) {
        this.fieldMappingRelations.add(new FieldMappingRelation(inputField, outputField, converterClass));
        return this;
    }

    /**
     * adds a mapping between two properties that have the same name.
     *
     * @param inputOutputField the field name in the input class and the output class
     * @param converterClass   the {@code Class} object representing the converter to use for the mapping
     * @return the same instance of {@code DozerBeanMappingHelper}
     */
    public DozerBeanMappingHelper addSameTypeMappingRelation(String inputOutputField,
                                                             Class<? extends CustomConverter> converterClass) {
        this.fieldMappingRelations.add(new FieldMappingRelation(
                inputOutputField, inputOutputField,
                converterClass
        ));
        return this;
    }

    /**
     * Builds the {@code BeanMappingBuilder} after all its properties were set.
     *
     * @return an instance of {@code BeanMappingBuilder} that can be registered in Dozer
     */
    public BeanMappingBuilder build() {
        return new BeanMappingBuilder() {
            protected void configure() {
                final TypeMappingBuilder typeMappingBuilder = mapping(getInputClass(), getOutputClass());
                getFieldMappingRelations().forEach(fieldMappingRelation ->
                        typeMappingBuilder.fields(
                                fieldMappingRelation.getInputField(),
                                fieldMappingRelation.getOutputField(),
                                customConverter(fieldMappingRelation.getConverterClass())
                        )
                );
            }
        };
    }
}
