@extends('app')

@section('content')

{!! Form::open(array('url' => '/assets/add', 'class' => 'form-horizontal')) !!}

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('assetcode','Asset Code: ')!!}
		</div>
    	<div class="col-sm-10">
      		{!!Form::text('assetcode')!!}
      		
    	</div>
	</div>

	<div class="form-group">
		<div class="control-label col-sm-2">
			{!!Form::label('preequip','Pre Existing Equipments:')!!}
		</div>
    	<div class="col-sm-10">
      		<select id="selector" name="selector" class="form-control" multiple="multiple">
      			@foreach($Enames as $index => $equipment)
      			<option value="{{$index + 1}}">{{$equipment->Name}}</option>
      			@endforeach
      		</select>
    	</div>
	</div>

	<div class="form-group">
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<input type="hidden" name="_token" value="{{ csrf_token() }}">
			<input type="submit" value="Submit">
		</div>
	</div>

{!! Form::close() !!}

@endsection