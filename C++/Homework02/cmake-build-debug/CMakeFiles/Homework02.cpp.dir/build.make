# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.14

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2019.1.3\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2019.1.3\bin\cmake\win\bin\cmake.exe" -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = C:\Users\denis\CLionProjects\Homework02

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = C:\Users\denis\CLionProjects\Homework02\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/Homework02.cpp.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/Homework02.cpp.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/Homework02.cpp.dir/flags.make

CMakeFiles/Homework02.cpp.dir/randomized_queue.cpp.obj: CMakeFiles/Homework02.cpp.dir/flags.make
CMakeFiles/Homework02.cpp.dir/randomized_queue.cpp.obj: ../randomized_queue.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\denis\CLionProjects\Homework02\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building CXX object CMakeFiles/Homework02.cpp.dir/randomized_queue.cpp.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Homework02.cpp.dir\randomized_queue.cpp.obj -c C:\Users\denis\CLionProjects\Homework02\randomized_queue.cpp

CMakeFiles/Homework02.cpp.dir/randomized_queue.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Homework02.cpp.dir/randomized_queue.cpp.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\denis\CLionProjects\Homework02\randomized_queue.cpp > CMakeFiles\Homework02.cpp.dir\randomized_queue.cpp.i

CMakeFiles/Homework02.cpp.dir/randomized_queue.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Homework02.cpp.dir/randomized_queue.cpp.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\denis\CLionProjects\Homework02\randomized_queue.cpp -o CMakeFiles\Homework02.cpp.dir\randomized_queue.cpp.s

CMakeFiles/Homework02.cpp.dir/subset.cpp.obj: CMakeFiles/Homework02.cpp.dir/flags.make
CMakeFiles/Homework02.cpp.dir/subset.cpp.obj: ../subset.cpp
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=C:\Users\denis\CLionProjects\Homework02\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Building CXX object CMakeFiles/Homework02.cpp.dir/subset.cpp.obj"
	C:\MinGW\bin\g++.exe  $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -o CMakeFiles\Homework02.cpp.dir\subset.cpp.obj -c C:\Users\denis\CLionProjects\Homework02\subset.cpp

CMakeFiles/Homework02.cpp.dir/subset.cpp.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing CXX source to CMakeFiles/Homework02.cpp.dir/subset.cpp.i"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -E C:\Users\denis\CLionProjects\Homework02\subset.cpp > CMakeFiles\Homework02.cpp.dir\subset.cpp.i

CMakeFiles/Homework02.cpp.dir/subset.cpp.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling CXX source to assembly CMakeFiles/Homework02.cpp.dir/subset.cpp.s"
	C:\MinGW\bin\g++.exe $(CXX_DEFINES) $(CXX_INCLUDES) $(CXX_FLAGS) -S C:\Users\denis\CLionProjects\Homework02\subset.cpp -o CMakeFiles\Homework02.cpp.dir\subset.cpp.s

# Object files for target Homework02.cpp
Homework02_cpp_OBJECTS = \
"CMakeFiles/Homework02.cpp.dir/randomized_queue.cpp.obj" \
"CMakeFiles/Homework02.cpp.dir/subset.cpp.obj"

# External object files for target Homework02.cpp
Homework02_cpp_EXTERNAL_OBJECTS =

Homework02.cpp.exe: CMakeFiles/Homework02.cpp.dir/randomized_queue.cpp.obj
Homework02.cpp.exe: CMakeFiles/Homework02.cpp.dir/subset.cpp.obj
Homework02.cpp.exe: CMakeFiles/Homework02.cpp.dir/build.make
Homework02.cpp.exe: CMakeFiles/Homework02.cpp.dir/linklibs.rsp
Homework02.cpp.exe: CMakeFiles/Homework02.cpp.dir/objects1.rsp
Homework02.cpp.exe: CMakeFiles/Homework02.cpp.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=C:\Users\denis\CLionProjects\Homework02\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_3) "Linking CXX executable Homework02.cpp.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\Homework02.cpp.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/Homework02.cpp.dir/build: Homework02.cpp.exe

.PHONY : CMakeFiles/Homework02.cpp.dir/build

CMakeFiles/Homework02.cpp.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\Homework02.cpp.dir\cmake_clean.cmake
.PHONY : CMakeFiles/Homework02.cpp.dir/clean

CMakeFiles/Homework02.cpp.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" C:\Users\denis\CLionProjects\Homework02 C:\Users\denis\CLionProjects\Homework02 C:\Users\denis\CLionProjects\Homework02\cmake-build-debug C:\Users\denis\CLionProjects\Homework02\cmake-build-debug C:\Users\denis\CLionProjects\Homework02\cmake-build-debug\CMakeFiles\Homework02.cpp.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/Homework02.cpp.dir/depend

