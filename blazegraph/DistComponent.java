//	----------------------------------------------------------
//	Copyright (c) 2017, Battelle Memorial Institute
//	All rights reserved.
//	----------------------------------------------------------

// package gov.pnnl.gridlabd.cim;

import java.io.*;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.RDFNode;

public abstract class DistComponent {
	static String OptionalString (QuerySolution soln, String parm, String def) {
		RDFNode nd = soln.get(parm);
		if (nd != null) {
			String str = nd.toString();
			if (str.length() > 0) {
				return str;
			}
		}
		return def;
	}

	static int OptionalInt (QuerySolution soln, String parm, int def) {
		RDFNode nd = soln.get(parm);
		if (nd != null) {
			String str = nd.toString();
			if (str.length() > 0) {
				return Integer.parseInt (str);
			}
		}
		return def;
	}

	static double OptionalDouble (QuerySolution soln, String parm, double def) {
		RDFNode nd = soln.get(parm);
		if (nd != null) {
			String str = nd.toString();
			if (str.length() > 0) {
				return Double.parseDouble (str);
			}
		}
		return def;
	}

	static boolean OptionalBoolean (QuerySolution soln, String parm, boolean def) {
		RDFNode nd = soln.get(parm);
		if (nd != null) {
			String str = nd.toString();
			if (str.length() > 0) {
				return Boolean.parseBoolean (str);
			}
		}
		return def;
	}

		/** prefix all bus names with `nd_` for GridLAB-D, so they "should" be unique
		 *	@param arg the root bus name, aka CIM name
		 *	@return nd_arg
		 */
		static String GldPrefixedNodeName (String arg) {
			return "nd_" + arg;
		}

		/** 
		 *	convert a CIM name to GridLAB-D name, replacing unallowed characters and prefixing for a bus/node
		 *	@param arg the root bus or component name, aka CIM name
		 *	@param bus to flag whether `nd_` should be prepended
		 *	@return the compatible name for GridLAB-D
		 */  
		static String GLD_Name (String arg, boolean bus) {			// GLD conversion
			String s = arg.replace (' ', '_');
			s = s.replace ('.', '_');
			s = s.replace ('=', '_');
			s = s.replace ('+', '_');
			s = s.replace ('^', '_');
			s = s.replace ('$', '_');
			s = s.replace ('*', '_');
			s = s.replace ('|', '_');
			s = s.replace ('[', '_');
			s = s.replace (']', '_');
			s = s.replace ('{', '_');
			s = s.replace ('}', '_');
			s = s.replace ('(', '_');
			s = s.replace (')', '_');
			if (bus) return GldPrefixedNodeName (s);
			return s;
		}

		public abstract String DisplayString();
		public abstract String GetKey();
}
