renjin-statet
=============

Renjin Integration with StatET

First cut at integration between Renjin and Stat-ET, with thanks
to Andreas Rytina.

The plugin should be functional, we really need to mavenize the build with
Tycho or something similar before it's ready for production use. This would
allow us to build and deploy the plugin via Jenkins, and pull in the latest 
version of Renjin (right now there's a copy of an old version of renjin in 
the source tree)
  
We also need to address licensing issues; we would like to release the plugin
under Apache or something equally liberal, but this may require relicensing
some part of Renjin as LGPL.



