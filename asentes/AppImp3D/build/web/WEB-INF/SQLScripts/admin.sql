# -----------------------------------------------------------------------
#    crée les autorisation du user local pour l'accés du serveur à la BD
# -----------------------------------------------------------------------

use imp3D;

drop user imp3D@localhost;
create user imp3D@localhost identified by 'imp3D31';

grant  select,insert,update,delete on imp3D.* to imp3D@localhost identified by 'imp3D31';
