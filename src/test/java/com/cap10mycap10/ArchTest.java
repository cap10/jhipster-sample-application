package com.cap10mycap10;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption;
import org.junit.jupiter.api.Test;

class ArchTest {

    @Test
    void servicesAndRepositoriesShouldNotDependOnWebLayer() {
        JavaClasses importedClasses = new ClassFileImporter()
            .withImportOption(ImportOption.Predefined.DO_NOT_INCLUDE_TESTS)
            .importPackages("com.cap10mycap10");

        noClasses()
            .that()
            .resideInAnyPackage("com.cap10mycap10.service..")
            .or()
            .resideInAnyPackage("com.cap10mycap10.repository..")
            .should()
            .dependOnClassesThat()
            .resideInAnyPackage("..com.cap10mycap10.web..")
            .because("Services and repositories should not depend on web layer")
            .check(importedClasses);
    }
}
