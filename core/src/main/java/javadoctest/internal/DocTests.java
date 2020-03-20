/**
 * Copyright (c) 2002-2014 "Neo Technology,"
 * Network Engine for Objects in Lund AB [http://neotechnology.com]
 *
 * This file is part of Neo4j.
 *
 * Neo4j is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package javadoctest.internal;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Discovers and caches parsed javadoc tests.
 */
public class DocTests
{
    private static Map<Class<?>, List<ExtractedDocTest>> allTests;

    /**
     * Returns the extracted doc tests that reference the given test class.
     * @param testClass JUnit test class containing the test methods for some snippets
     */
    public static List<ExtractedDocTest> testsFor( Class<?> testClass )
    {
        ensureDocTestsLoaded();
        List<ExtractedDocTest> tests = allTests.get( testClass );
        if(tests == null)
        {
            throw new AssertionError( "No javadoc tests found for class " + testClass.getName() );
        }
        return tests;
    }

    private static void ensureDocTestsLoaded()
    {
        if(allTests == null)
        {
            allTests = new HashMap<>();

            FileWalker.walk( Paths.get( "." ), public_java_sources, path -> {
                try
                {
                    for ( ExtractedDocTest docTest : new DocTestExtractor().extractFrom( path ) )
                    {
                        List<ExtractedDocTest> testsForClass = allTests
                            .computeIfAbsent(docTest.testClass(), k -> new LinkedList<>());
                        testsForClass.add( docTest );
                    }

                }
                catch ( IOException e )
                {
                    throw new RuntimeException( e );
                }
            });
        }
    }

    private static final Predicate<Path> public_java_sources =
        path -> path.toString().endsWith( ".java" );

}