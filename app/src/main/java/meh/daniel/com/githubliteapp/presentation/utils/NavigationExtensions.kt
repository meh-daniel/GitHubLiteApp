//package meh.daniel.com.githubliteapp.presentation
//
//import androidx.annotation.IdRes
//import androidx.fragment.app.Fragment
//import androidx.navigation.NavController
//import androidx.navigation.NavDirections
//import androidx.navigation.findNavController
//import meh.daniel.com.githubliteapp.R
//
//fun Fragment.activityNavController() = requireActivity().findNavController(R.id.main_nav)
//
//fun NavController.navigateSafely(@IdRes actionId: Int) {
//    currentDestination?.getAction(actionId)?.let { navigate(actionId) }
//}
//
//fun NavController.navigateSafely(directions: NavDirections) {
//    currentDestination?.getAction(directions.actionId)?.let { navigate(directions) }
//}