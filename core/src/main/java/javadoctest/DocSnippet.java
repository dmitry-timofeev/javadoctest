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
package javadoctest;

public interface DocSnippet
{
    /**
     * Explicitly add a class that needs to be imported for the snippet to work.
     * @param cls the class or interface to import
     * @return this instance
     */
    DocSnippet addImport( Class<?> cls );

    /**
     * Compile and run the code. If your snippet requires {@link #addImport(Class) imports},
     * this should get called after you've organized those.
     *
     * @throws Exception the exception occurred during the compilation or the execution of
     *     this snippet
     */
    void run() throws Exception;

    /**
     * After the snippet has ben {@link #run() run}, you can access variables declared inside the
     * snippet here.
     * @param variableName the name of the variable
     * @param <T> the type of the variable
     * @return the variable
     */
    <T> T get( String variableName );
}