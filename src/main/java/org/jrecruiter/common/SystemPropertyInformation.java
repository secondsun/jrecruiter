/*
 *  http://www.jrecruiter.org
 *
 *  Disclaimer of Warranty.
 *
 *  Unless required by applicable law or agreed to in writing, Licensor provides
 *  the Work (and each Contributor provides its Contributions) on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied,
 *  including, without limitation, any warranties or conditions of TITLE,
 *  NON-INFRINGEMENT, MERCHANTABILITY, or FITNESS FOR A PARTICULAR PURPOSE. You are
 *  solely responsible for determining the appropriateness of using or
 *  redistributing the Work and assume any risks associated with Your exercise of
 *  permissions under this License.
 *
 */
package org.jrecruiter.common;

import java.util.List;

/**
 * Provides access to the JVM System Properties in a type-safe manner.
 *
 * @author Gunnar Hillert
 * @version $Id: Constants.java 532 2009-08-28 03:50:24Z ghillert $
 */
public enum SystemPropertyInformation {

    JAVA_VERSION(                 "java.version",  	               "Java Runtime Environment version"),
    JAVA_VENDOR(                  "java.vendor",  	               "Java Runtime Environment vendor"),
    JAVA_VENDOR_URL(              "java.vendor.url",  	           "Java vendor URL"),
    JAVA_HOME(                    "java.home",  	               "Java installation directory"),
    JAVA_VM_SPECIFICATION_VERSION("java.vm.specification.version", "Java Virtual Machine specification version"),
    JAVA_VM_SPECIFICATION_VENDOR( "java.vm.specification.vendor",  "Java Virtual Machine specification vendor"),
    JAVA_VM_SPECIFICATION_NAME(   "java.vm.specification.name",    "Java Virtual Machine specification name"),
    JAVA_VM_VERISON(              "java.vm.version",  	           "Java Virtual Machine implementation version"),
    JAVA_VM_VENDOR(               "java.vm.vendor",  	           "Java Virtual Machine implementation vendor"),
    JAVA_VM_NAME(                 "java.vm.name",  	               "Java Virtual Machine implementation name"),
    JAVA_SPECIFICATION_VERSION(   "java.specification.version",    "Java Runtime Environment specification version"),
    JAVA_SPECIFICATION_VENDOR(    "java.specification.vendor",     "Java Runtime Environment specification vendor"),
    JAVA_SPECIFICATION_NAME(      "java.specification.name",  	   "Java Runtime Environment specification name"),
    JAVA_CLASS_VERSION(           "java.class.version",  	       "Java class format version number"),
    JAVA_CLASS_PATH(              "java.class.path",  	           "Java class path"),
    JAVA_LIBRARY_PATH(            "java.library.path",  	       "List of paths to search when loading libraries"),
    JAVA_IO_TMPDIR(               "java.io.tmpdir",  	           "Default temp file path"),
    JAVA_COMPILER(                "java.compiler",  	           "Name of JIT compiler to use"),
    JAVA_EXT_DIR(                 "java.ext.dirs",  	           "Path of extension directory or directories"),
    OS_NAME(                      "os.name",  	                   "Operating system name"),
    OS_ARCH(                      "os.arch",  	                   "Operating system architecture"),
    OS_VERSION(                   "os.version",  	               "Operating system version"),
    FILE_SEPARATOR(               "file.separator",  	           "File separator (\"/\" on UNIX)"),
    PATH_SEPARATOR(               "path.separator",  	           "Path separator (\":\" on UNIX)"),
    LINE_SEPARATOR(               "line.separator",  	           "Line separator (\"\n\" on UNIX)"),
    USER_NAME(                    "user.name",  	               "User's account name"),
    USER_HOME(                    "user.home",  	               "User's home directory"),
    USER_DIR(                     "user.dir",  	                   "User's current working directory");

    private String propertyKey;
    private String description;

    private SystemPropertyInformation(final String propertyKey, final String description) {
        this.propertyKey = propertyKey;
        this.description = description;
    }

    public String getPropertyKey() {
        return propertyKey;
    }

    public void setPropertyKey(String propertyKey) {
        this.propertyKey = propertyKey;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPropertyValue() {
        return System.getProperty(this.propertyKey);
    }

    public static List<SystemPropertyInformation> getSystemPropertyValuesAsList() {

        final List<SystemPropertyInformation>systemPropertyValues = CollectionUtils.getArrayList();

        for (SystemPropertyInformation systemProperty : SystemPropertyInformation.values()) {
            systemPropertyValues.add(systemProperty);
        }

        return systemPropertyValues;
    }

}

